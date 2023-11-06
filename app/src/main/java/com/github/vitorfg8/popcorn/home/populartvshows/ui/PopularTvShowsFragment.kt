package com.github.vitorfg8.popcorn.home.populartvshows.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.github.vitorfg8.popcorn.R
import com.github.vitorfg8.popcorn.databinding.FragmentPopularTvShowsBinding
import com.github.vitorfg8.popcorn.details.ui.DetailsActivity
import com.github.vitorfg8.popcorn.home.populartvshows.ui.dataUi.PopularTvShowDataUi
import com.github.vitorfg8.popcorn.home.populartvshows.ui.viewmodel.PopularTvShowsViewModel
import com.github.vitorfg8.popcorn.utils.Constants.TV
import com.github.vitorfg8.popcorn.utils.State
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularTvShowsFragment : Fragment() {

    private var binding: FragmentPopularTvShowsBinding? = null
    private val popularTvShowsViewModel by viewModel<PopularTvShowsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopularTvShowsBinding.inflate(inflater, container, false)
        popularTvShowsViewModel.getPopularTvSeries()
        observePopularTvShowsList()
        return binding?.root
    }

    private fun observePopularTvShowsList() {
        val skeleton = binding?.recyclerTvShows?.applySkeleton(R.layout.item_poster, 5)
        popularTvShowsViewModel.popularTvShows.observe(viewLifecycleOwner) { state ->
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
        state: State.Success<List<PopularTvShowDataUi>>
    ) {
        skeleton?.showOriginal()
        showSuccessState(state.data)
    }

    private fun showSuccessState(data: List<PopularTvShowDataUi>) {
        binding?.apply {
            recyclerTvShows.isVisible = true
            errorCard.root.isVisible = false
            val popularTvShowsAdapter = PopularTvShowsAdapter {
                startActivity(DetailsActivity.getIntent(requireContext(), it, TV))
            }
            popularTvShowsAdapter.submitList(data)
            recyclerTvShows.adapter = popularTvShowsAdapter
        }
    }

    private fun showErrorState() {
        binding?.recyclerTvShows?.isVisible = false
        binding?.errorCard?.root?.isVisible = true
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}