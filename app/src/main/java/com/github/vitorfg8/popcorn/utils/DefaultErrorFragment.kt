package com.github.vitorfg8.popcorn.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.vitorfg8.popcorn.databinding.FragmentDefaultErrorBinding

class DefaultErrorFragment : Fragment() {

    private var binding: FragmentDefaultErrorBinding? = null
    private lateinit var onTryAgainClicked: () -> Unit

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDefaultErrorBinding.inflate(inflater, container, false)
        setupTryAgainClick()
        return binding?.root
    }

    private fun setupTryAgainClick() {
        binding?.tryAgainButton?.setOnClickListener {
            onTryAgainClicked.invoke()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(onTryAgainClick: () -> Unit) =
            DefaultErrorFragment().apply {
                onTryAgainClicked = onTryAgainClick
            }
    }
}