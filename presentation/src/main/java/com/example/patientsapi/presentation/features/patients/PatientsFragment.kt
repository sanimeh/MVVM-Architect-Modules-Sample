package com.example.patientsapi.presentation.features.patients

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
import com.example.patientsapi.presentation.databinding.FragmentPatientsBinding
import com.example.patientsapi.presentation.features.patients.adapters.PatientsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PatientsFragment : Fragment() {

    private lateinit var bidning: FragmentPatientsBinding

    private val viewModel: PatientsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bidning = FragmentPatientsBinding.inflate(layoutInflater)
        return bidning.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.patientsStateFlow.collect() { response ->
                if (response.isNotEmpty())
                    bidning.recyclerView.adapter = PatientsAdapter(response)
                //Toast.makeText(requireContext(), response.toString(), Toast.LENGTH_LONG).show()

            }
        }
        lifecycleScope.launch {
            viewModel.patientsLoadingStateFlow.collect() { show ->
                bidning.progressCircular.isVisible = show
            }
        }
        lifecycleScope.launch {
            viewModel.patientsErorsStateFlow.collect() { response ->
                if (response != null)
                    Log.d("TAG00", " Error: $response")
            }
        }

    }
}