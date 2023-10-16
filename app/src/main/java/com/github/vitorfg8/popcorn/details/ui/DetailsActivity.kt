package com.github.vitorfg8.popcorn.details.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.vitorfg8.popcorn.R
import com.github.vitorfg8.popcorn.databinding.ActivityDetailsBinding
import com.github.vitorfg8.popcorn.details.ui.dataui.DetailsDataUi
import com.github.vitorfg8.popcorn.details.ui.viewmodel.DetailsViewModel
import com.github.vitorfg8.popcorn.utils.DefaultErrorFragment
import com.github.vitorfg8.popcorn.utils.State
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {

    private var binding: ActivityDetailsBinding? = null
    private val detailsViewModel by viewModel<DetailsViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        getDetails()
    }

    private fun getDetails() {
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
                is State.Loading -> showFragment(DetailsLoadingFragment.newInstance())
                is State.Success<DetailsDataUi> -> showFragment(DetailsFragment.newInstance(state.data))
                is State.Error -> showFragment(DefaultErrorFragment.newInstance {
                    getDetails()
                })
            }
        }
    }


    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerDetails, fragment)
            .commit()
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