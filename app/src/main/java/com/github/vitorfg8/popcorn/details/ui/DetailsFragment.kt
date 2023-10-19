package com.github.vitorfg8.popcorn.details.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.BundleCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.github.vitorfg8.popcorn.R
import com.github.vitorfg8.popcorn.databinding.FragmentDetailsBinding
import com.github.vitorfg8.popcorn.details.cast.ui.CastFragment
import com.github.vitorfg8.popcorn.details.ui.dataui.DetailsDataUi
import com.google.android.material.chip.Chip
import kotlin.math.abs


class DetailsFragment : Fragment() {
    private var binding: FragmentDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        setupDetails()
        setupCastList()
        return binding?.root
    }

    private fun setupCastList() {
        arguments?.let {
            val details = BundleCompat.getParcelable(it, DETAILS, DetailsDataUi::class.java)
            if (details != null) {
                val castFragment = CastFragment.newInstance(details.mediaType, details.id)
                childFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerViewCast, castFragment)
                    .commit()
            }
        }
    }

    private fun setupDetails() {
        arguments?.let {
            val details = BundleCompat.getParcelable(it, DETAILS, DetailsDataUi::class.java)
            if (details != null) {
                binding?.apply {
                    Glide.with(requireContext())
                        .load(details.posterUrl)
                        .into(appBarImage)
                    setupGenres(details.genres)
                    textViewSynopsis.text = details.overview
                    textViewDuration.text = details.runtime
                    textViewVotes.text = details.voteAverage
                    setupToolbar(details.title)
                }
            }
        }
    }

    private fun setupToolbar(title: String) {
        val detailsActivity = activity as? DetailsActivity
        binding?.apply {
            detailsActivity?.setSupportActionBar(toolbar)
            detailsActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            toolbar.setNavigationOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }
            toolbar.title = ""
            setOffsetAnimation(title, detailsActivity)
        }
    }

    private fun FragmentDetailsBinding.setOffsetAnimation(
        title: String,
        detailsActivity: DetailsActivity?
    ) {
        appbar.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            val maxScroll = appBarLayout.totalScrollRange
            val percentage = abs(verticalOffset).toFloat() / maxScroll.toFloat()
            collapsingToolbar.title = if (percentage >= 0.99f) title else ""
            detailsActivity?.supportActionBar?.setHomeAsUpIndicator(
                if (percentage >= 0.99f) R.drawable.ic_chevron_back else R.drawable.navigation_icon_expanded
            )
        }
    }

    private fun setupGenres(genres: List<String?>?) {
        genres?.forEach {
            val chip = Chip(requireContext())
            chip.text = it
            chip.isClickable = false
            binding?.chipGroup?.addView(chip)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        private const val DETAILS = "DETAILS"

        @JvmStatic
        fun newInstance(detailsDataUi: DetailsDataUi) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(DETAILS, detailsDataUi)
                }
            }
    }
}