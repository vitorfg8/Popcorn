package com.github.vitorfg8.popcorn.home.populartvshows.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopularTvShowsBinding.inflate(inflater, container, false)
        observePopularTvShowsList()
        return binding?.root
    }

    private fun observePopularTvShowsList() {
        popularTvShowsViewModel.popularTvShows.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Loading -> {
                    // TODO:
                }

                is State.Success -> showSuccessState(state.data)
                is State.Error -> showErrorState()
            }
        }
    }

    private fun showSuccessState(data: List<PopularTvShowDataUi>) {
        binding?.recyclerTvShows?.isVisible = true
        binding?.errorCard?.root?.isVisible = false
        val popularTvShowsAdapter = PopularTvShowsAdapter {
            startActivity(DetailsActivity.getIntent(requireContext(), it, TV))
        }
        popularTvShowsAdapter.submitList(data)
        binding?.recyclerTvShows?.adapter = popularTvShowsAdapter
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