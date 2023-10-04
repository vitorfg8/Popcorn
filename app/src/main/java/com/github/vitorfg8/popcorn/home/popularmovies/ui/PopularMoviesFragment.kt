package com.github.vitorfg8.popcorn.home.popularmovies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
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
        observePopularMoviesList()
        return binding?.root
    }

    private fun observePopularMoviesList() {
        popularMoviesViewModel.popularMovies.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Loading -> {
                    // TODO:
                }

                is State.Success<List<PopularMovieDataUi>> -> showSuccessState(state.data)
                is State.Error -> showErrorState()
            }
        }
    }

    private fun showSuccessState(data: List<PopularMovieDataUi>) {
        binding?.recyclerMovies?.isVisible = true
        binding?.errorCard?.root?.isVisible = false

        val popularMoviesAdapter = PopularMoviesAdapter {
            startActivity(DetailsActivity.getIntent(requireContext(), it, MOVIE))
        }
        popularMoviesAdapter.submitList(data)
        binding?.recyclerMovies?.adapter = popularMoviesAdapter
    }

    private fun showErrorState() {
        binding?.recyclerMovies?.isVisible = false
        binding?.errorCard?.root?.isVisible = true
    }
}