package com.github.vitorfg8.popcorn.home.trends.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.github.vitorfg8.popcorn.R
import com.github.vitorfg8.popcorn.databinding.FragmentTrendsBinding
import com.github.vitorfg8.popcorn.details.ui.DetailsActivity
import com.github.vitorfg8.popcorn.home.trends.ui.dataUi.TrendDataUi
import com.github.vitorfg8.popcorn.home.trends.ui.viewmodel.TrendsViewModel
import com.github.vitorfg8.popcorn.utils.State
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs

class TrendsFragment : Fragment() {

    private val trendsViewModel by viewModel<TrendsViewModel>()
    private lateinit var infinitePageAdapter: InfinitePageAdapter
    private var binding: FragmentTrendsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrendsBinding.inflate(inflater, container, false)
        setupViewPager()
        setUpTransformer()
        observeTrendsList()
        return binding?.root
    }

    private fun setUpTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(20))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.8f + r * 0.14f
        }
        binding?.viewPager?.setPageTransformer(transformer)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


    private fun onInfinitePageChangeCallback(listSize: Int) {
        binding?.viewPager?.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    when (binding?.viewPager?.currentItem) {
                        listSize - 1 -> binding?.viewPager?.setCurrentItem(1, false)
                        0 -> binding?.viewPager?.setCurrentItem(listSize - 2, false)
                    }
                }
            }
        })
    }

    private fun observeTrendsList() {
        val skeleton = binding?.viewPager?.applySkeleton(R.layout.item_trend, 3)
        trendsViewModel.trends.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Loading -> skeleton?.showSkeleton()
                is State.Success -> handleSuccess(skeleton, state)
                is State.Error -> handleError(skeleton)
            }
        }
    }

    private fun handleError(skeleton: Skeleton?) {
        skeleton?.showOriginal()
        showErrorState()
    }

    private fun handleSuccess(
        skeleton: Skeleton?,
        state: State.Success<List<TrendDataUi>>
    ) {
        skeleton?.showOriginal()
        showSuccessState(state.data)
    }

    private fun showSuccessState(data: List<TrendDataUi>) {
        binding?.apply {
            errorCard.root.isVisible = false
            viewPager.isVisible = true
            setupAdapter(data, viewPager)
        }
    }

    private fun showErrorState() {
        binding?.errorCard?.root?.isVisible = true
        binding?.viewPager?.isVisible = false
    }

    private fun setupViewPager() {
        binding?.viewPager?.apply {
            offscreenPageLimit = 3
            clipToPadding = false
            clipChildren = false
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
    }

    private fun setupAdapter(trends: List<TrendDataUi>, viewPager: ViewPager2) {
        infinitePageAdapter = InfinitePageAdapter(trends) { id, mediaType ->
            startActivity(DetailsActivity.getIntent(requireContext(), id, mediaType))
        }
        viewPager.adapter = infinitePageAdapter
        viewPager.currentItem = 1
        onInfinitePageChangeCallback(trends.size + 2)
    }
}