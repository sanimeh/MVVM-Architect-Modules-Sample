package com.example.patientsapi.presentation.features.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.patientsapi.presentation.databinding.FragmentAddPatientBinding
import kotlinx.coroutines.launch

class DetailsPatientFragment : Fragment() {
    private lateinit var binding: FragmentAddPatientBinding
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
        //indicating an error here, this version is uploaded with its run time error! Part8 min 11:38
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("id")
        initObserver()
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.detailsErorsStateFlow.collect { response ->
                if (response != null)
                    Toast.makeText(requireContext(), "response : $response", Toast.LENGTH_SHORT)
                        .show()

            }
        }
        lifecycleScope.launch {
            viewModel.detailssStateFlow.collect {
                viewModel.detailsLoadingStateFlow.collect { show ->
                    binding.progressCircular.isVisible = show
                }
            }
        }
        lifecycleScope.launch {
            viewModel.detailsErorsStateFlow.collect { response ->
                if (response != null) {
                    Log.d("TAG", response.toString())
                }
            }
        }
    }
}