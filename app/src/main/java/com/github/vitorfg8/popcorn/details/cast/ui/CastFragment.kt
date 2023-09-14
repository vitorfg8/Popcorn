package com.github.vitorfg8.popcorn.details.cast.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.vitorfg8.popcorn.databinding.FragmentCastBinding
import com.github.vitorfg8.popcorn.utils.State
import org.koin.androidx.viewmodel.ext.android.viewModel

class CastFragment : Fragment() {

    private var binding: FragmentCastBinding? = null
    private val castViewModel by viewModel<CastViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCastBinding.inflate(inflater, container, false)
        observeCastList()
        return binding?.root
    }

    private fun observeCastList() {
        val mediaType = arguments?.getString(MEDIA_TYPE)
        val id = arguments?.getInt(ID)
        if (mediaType != null && id != null) {
            castViewModel.getCast(mediaType, id)
            castViewModel.cast.observe(viewLifecycleOwner) { state ->
                when (state) {
                    is State.Loading -> {
                        //TODO:
                    }

                    is State.Success -> {
                        val adapter = CastAdapter()
                        adapter.submitList(state.data)
                        binding?.recyclerCast?.adapter = adapter
                    }

                    is State.Error -> {
                        //TODO:
                    }
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        private const val MEDIA_TYPE = "mediaType"
        private const val ID = "id"
        fun newInstance(mediaType: String, id: Int) = CastFragment().apply {
            arguments = Bundle().apply {
                putString(MEDIA_TYPE, mediaType)
                putInt(ID, id)
            }
        }
    }
}