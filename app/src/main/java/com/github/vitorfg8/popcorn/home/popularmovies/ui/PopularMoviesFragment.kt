package com.github.vitorfg8.popcorn.home.popularmovies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.github.vitorfg8.popcorn.R
import com.github.vitorfg8.popcorn.databinding.FragmentPopularMoviesBinding
import com.github.vitorfg8.popcorn.details.ui.DetailsActivity
import com.github.vitorfg8.popcorn.home.popularmovies.ui.dataUi.PopularMovieDataUi
import com.github.vitorfg8.popcorn.home.popularmovies.ui.viewmodel.PopularMoviesViewModel
import com.github.vitorfg8.popcorn.utils.Constants.MOVIE
import com.github.vitorfg8.popcorn.utils.State
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularMoviesFragment : Fragment() {

    private val popularMoviesViewModel by viewModel<PopularMoviesViewModel>()
    private var binding: FragmentPopularMoviesBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopularMoviesBinding.inflate(inflater, container, false)
        popularMoviesViewModel.getPopularMovies()
        observePopularMoviesList()
        return binding?.root
    }

    private fun observePopularMoviesList() {
        val skeleton = binding?.recyclerMovies?.applySkeleton(R.layout.item_poster, 5)
        popularMoviesViewModel.popularMovies.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Loading -> skeleton?.showSkeleton()
                is State.Success<List<PopularMovieDataUi>> -> handleSuccess(skeleton, state)
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
        state: State.Success<List<PopularMovieDataUi>>
    ) {
        skeleton?.showOriginal()
        showSuccessState(state.data)
    }

    private fun showSuccessState(data: List<PopularMovieDataUi>) {
        binding?.apply {
            recyclerMovies.isVisible = true
            errorCard.root.isVisible = false
            val popularMoviesAdapter = PopularMoviesAdapter {
                startActivity(DetailsActivity.getIntent(requireContext(), it, MOVIE))
            }
            popularMoviesAdapter.submitList(data)
            recyclerMovies.adapter = popularMoviesAdapter
        }
    }


    private fun showErrorState() {
        binding?.recyclerMovies?.isVisible = false
        binding?.errorCard?.root?.isVisible = true
    }
}