package com.example.patientsapi.presentation.features.patients

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.patientsapi.presentation.R
import com.example.patientsapi.presentation.databinding.FragmentPatientsBinding
import com.example.patientsapi.presentation.features.patients.adapters.PatientsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PatientsFragment : Fragment() {

    private lateinit var bidning: FragmentPatientsBinding

    private val viewModel: PatientsViewModel by viewModels()
    private lateinit var adapter: PatientsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bidning = FragmentPatientsBinding.inflate(layoutInflater)
        return bidning.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initObserver()
        initListener()

    }

    private fun initAdapter() {
        adapter = PatientsAdapter()
        bidning.recyclerView.adapter = adapter
    }

    private fun initListener() {
        bidning.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.addPatientsFragment)
        }
        bidning.swipRefresh.setOnRefreshListener {
            lifecycleScope.launch {
                delay(3000)
                bidning.swipRefresh.isRefreshing = false
            }
        }
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.patientsStateFlow.collect() { response ->
                if (response != null)
                    adapter.submitList(response)
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