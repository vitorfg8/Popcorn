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
    private lateinit var onCloseClicked: () -> Unit

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDefaultErrorBinding.inflate(inflater, container, false)
        setupButtons()
        return binding?.root
    }

    private fun setupButtons() {
        binding?.tryAgainButton?.setOnClickListener {
            onTryAgainClicked.invoke()
        }
        binding?.imageClose?.setOnClickListener {
            onCloseClicked.invoke()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(onTryAgainClick: () -> Unit, onCloseClick: () -> Unit) =
            DefaultErrorFragment().apply {
                onTryAgainClicked = onTryAgainClick
                onCloseClicked = onCloseClick
            }
    }
}