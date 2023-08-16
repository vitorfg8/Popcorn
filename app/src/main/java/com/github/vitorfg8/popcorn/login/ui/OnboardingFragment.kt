package com.github.vitorfg8.popcorn.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.vitorfg8.popcorn.databinding.FragmentOnboardingBinding

class OnboardingFragment : Fragment() {

    private var binding: FragmentOnboardingBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        binding?.buttonLogin?.setOnClickListener {

        }
        return binding?.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = OnboardingFragment()
    }
}