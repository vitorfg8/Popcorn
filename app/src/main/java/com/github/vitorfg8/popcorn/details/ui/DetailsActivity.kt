package com.github.vitorfg8.popcorn.details.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.github.vitorfg8.popcorn.databinding.ActivityDetailsBinding
import com.github.vitorfg8.popcorn.details.ui.dataui.DetailsDataUi
import com.github.vitorfg8.popcorn.details.ui.viewmodel.DetailsViewModel
import com.github.vitorfg8.popcorn.utils.State
import com.google.android.material.chip.Chip
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {

    private var binding: ActivityDetailsBinding? = null
    private val detailsViewModel by viewModel<DetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setDetailsIntent()
    }

    private fun setDetailsIntent() {
        val id = intent.extras?.getInt(ID)
        val mediaType = intent.extras?.getString(MEDIA_TYPE)
        if (id != null && mediaType != null) {
            detailsViewModel.getDetails(id, mediaType)
            observeMovieDetails()
        }
    }

    private fun observeMovieDetails() {
        detailsViewModel.details.observe(this) { state ->
            when (state) {
                is State.Loading -> {
                    //TODO:
                }

                is State.Success<DetailsDataUi> -> {
                    setupDetails(state.data)
                }

                is State.Error -> {
                    //TODO:
                }
            }
        }
    }

    private fun setupDetails(details: DetailsDataUi) {
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
        private const val MEDIA_TYPE = "mediaType"
        fun getIntent(context: Context, id: Int, mediaType: String): Intent {
            return Intent(context, DetailsActivity::class.java).apply {
                putExtra(ID, id)
                putExtra(MEDIA_TYPE, mediaType)
            }
        }
    }
}