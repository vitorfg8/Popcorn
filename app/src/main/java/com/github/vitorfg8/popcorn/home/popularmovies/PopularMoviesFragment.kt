package com.github.vitorfg8.popcorn.home.popularmovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.vitorfg8.popcorn.databinding.FragmentPopularMoviesBinding
import com.github.vitorfg8.popcorn.home.popularmovies.ui.PopularMoviesAdapter
import com.github.vitorfg8.popcorn.home.popularmovies.ui.viewmodel.PopularMoviesViewModel
import com.github.vitorfg8.popcorn.home.popularmovies.ui.viewmodel.Result
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
        popularMoviesViewModel.popularMovies.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Success -> {
                    val popularMoviesAdapter = PopularMoviesAdapter {

                    }
                    popularMoviesAdapter.submitList(result.list)
                    binding?.recyclerTvSeries?.adapter = popularMoviesAdapter
                }

                is Result.Error -> {
                    //TODO
                }
            }
        }
    }
}