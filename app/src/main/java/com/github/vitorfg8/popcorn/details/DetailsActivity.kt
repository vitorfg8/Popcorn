package com.github.vitorfg8.popcorn.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.github.vitorfg8.popcorn.databinding.ActivityDetailsBinding
import com.github.vitorfg8.popcorn.details.movie.ui.MovieDetailsViewModel
import com.github.vitorfg8.popcorn.details.movie.ui.dataui.MovieDetailsDataUi
import com.github.vitorfg8.popcorn.utils.State
import com.google.android.material.chip.Chip
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {

    private var binding: ActivityDetailsBinding? = null
    private val movieDetailsViewModel by viewModel<MovieDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setDetailsIntent()
    }

    private fun setDetailsIntent() {
        intent.extras?.getInt(ID)?.let {
            movieDetailsViewModel.getMovieDetails(it)
            observeMovieDetails()
        }
    }

    private fun observeMovieDetails() {
        movieDetailsViewModel.movieDetails.observe(this) { state ->
            when (state) {
                is State.Loading -> {
                    //TODO:
                }

                is State.Success<MovieDetailsDataUi> -> {
                    setupDetails(state.data)
                }

                is State.Error -> {
                    //TODO:
                }
            }
        }
    }

    private fun setupDetails(details: MovieDetailsDataUi) {
        binding?.apply {
            Glide.with(applicationContext).load(details.posterUrl).into(appBarImage)
            setupGenres(details.genres)
            textViewSynopsis.text = details.overview
            textViewDuration.text = details.runtime
            textViewVotes.text = details.voteAverage
            setSupportActionBar(toolbar)
            toolbar.title = details.title
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupGenres(genres: List<String?>?) {
        genres?.forEach {
            val chip = Chip(this)
            chip.text = it
            binding?.chipGroup?.addView(chip)
        }
    }

    companion object {
        private const val ID = "id"
        fun getIntent(context: Context, movieId: Int): Intent {
            return Intent(context, DetailsActivity::class.java).apply {
                putExtra(ID, movieId)
            }
        }
    }
}