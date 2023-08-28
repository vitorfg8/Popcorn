package com.github.vitorfg8.popcorn.home.trends.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.vitorfg8.popcorn.R
import com.github.vitorfg8.popcorn.home.trends.presentation.Result
import com.github.vitorfg8.popcorn.home.trends.presentation.TrendsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass.
 * Use the [TrendsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TrendsFragment : Fragment() {

    private val trendsViewModel by viewModel<TrendsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
/*            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)*/
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        observeTrendsList()
        return inflater.inflate(R.layout.fragment_trends, container, false)
    }

    private fun observeTrendsList() {
        trendsViewModel.trends.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> {
                    Log.d("teste", "observeTrendsList: ${it.list}")
                }

                is Result.Error -> {
                    Log.e("teste", "observeTrendsList: $it")
                }
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TrendsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TrendsFragment().apply {
                arguments = Bundle().apply {
/*                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)*/
                }
            }
    }
}