package com.example.patientsapi.presentation.features.patients

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.patientsapi.domain.model.delete.PatientDeleteResponse
import com.example.patientsapi.domain.model.patients.PatientResponse
import com.example.patientsapi.presentation.R
import com.example.patientsapi.presentation.databinding.FragmentPatientsBinding
import com.example.patientsapi.presentation.features.patients.adapters.PatientsAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
        adapter = PatientsAdapter(::deletePatient,::onClickItem)
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
            viewModel.patientsStateFlow.collect(::onSuccessPatients)
        }
        lifecycleScope.launch {
            viewModel.patientsLoadingStateFlow.collect(::onLoadingPatients)
        }
        lifecycleScope.launch {
            viewModel.patientsErorsStateFlow.collect(::onErrorPatients)
        }
        lifecycleScope.launch {
            viewModel.deletePatientLiveData.observe(viewLifecycleOwner, ::onPatientDeletedSuccess)
        }
    }

    private fun onPatientDeletedSuccess(response: PatientDeleteResponse?) {
        if (response != null) {
            Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT).show()
            viewModel.getPatients()
        }

    }

    private fun onSuccessPatients(response: List<PatientResponse>?) {
        if (response != null)
            adapter.submitList(response)
    }

    private fun onLoadingPatients(show: Boolean) {
        bidning.progressCircular.isVisible = show
    }

    private fun onErrorPatients(response: Exception?) {
        if (response != null)
            Log.d("TAG00", " Error: $response")
    }

    fun deletePatient(id: String) {
        MaterialAlertDialogBuilder(requireContext()).setMessage("Are you sure to delete this Patient?")
            .setNegativeButton("no") { dialog, _ ->
                dialog.dismiss()
            }.setNegativeButton("yes") { dialog, _ ->
                viewModel.DeletePatient(id)
                dialog.dismiss()
            }.show()
    }

    private fun onClickItem(id: String) {
        findNavController().navigate(R.id.detailsPatientFragment, bundleOf("id" to id))
    }
}