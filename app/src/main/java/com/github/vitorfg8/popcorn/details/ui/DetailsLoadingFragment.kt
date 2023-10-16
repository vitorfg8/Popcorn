package com.github.vitorfg8.popcorn.details.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.faltenreich.skeletonlayout.applySkeleton
import com.github.vitorfg8.popcorn.R
import com.github.vitorfg8.popcorn.databinding.FragmentDetailsLoadingBinding

class DetailsLoadingFragment : Fragment() {

    private var binding: FragmentDetailsLoadingBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsLoadingBinding.inflate(inflater, container, false)
        binding?.skeletonLayout?.showSkeleton()
        val skeleton = binding?.recyclerCast?.applySkeleton(R.layout.item_cast, 5)
        skeleton?.showSkeleton()
        return binding?.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = DetailsLoadingFragment()
    }
}