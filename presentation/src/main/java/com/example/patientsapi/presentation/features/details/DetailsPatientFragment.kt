package com.example.patientsapi.presentation.features.details

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.patientsapi.core.BaseFragment
import com.example.patientsapi.presentation.R
import com.example.patientsapi.presentation.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsPatientFragment : BaseFragment<FragmentDetailsBinding>(R.layout.fragment_details) {

    private val viewModel: DetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("id")
        initObserver()
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.detailssStateFlow.collect { response ->
                if (response != null)
                    binding.model = response
                //Toast.makeText(requireContext(), "response : $response", Toast.LENGTH_SHORT).show()
            }
        }
        lifecycleScope.launch {
            viewModel.detailsLoadingStateFlow.collect { show ->
                binding.progressCircular.isVisible = show
            }
        }

        lifecycleScope.launch {
            viewModel.detailsErorsStateFlow.collect { response ->
                if (response != null) {
                    Log.d("TAG00", response.toString())
                }
            }
        }
    }
}