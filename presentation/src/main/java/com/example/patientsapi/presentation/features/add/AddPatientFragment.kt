package com.example.patientsapi.presentation.features.add

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.patientsapi.core.BaseFragment
import com.example.patientsapi.domain.model.add.AddPatientRequest
import com.example.patientsapi.presentation.R
import com.example.patientsapi.presentation.databinding.FragmentAddPatientBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddPatientFragment : BaseFragment<FragmentAddPatientBinding>(R.layout.fragment_add_patient) {
    private val viewModel: AddPatientViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        initListener()
    }

    private fun infoValid(): Boolean {
        var isValid = true
        if (binding.editTextFullName.text?.isEmpty() == true) {
            isValid = false
            binding.textFullName.error = "Name is Empty"
        } else {
            binding.textFullName.error = null
        }
        if (binding.editTextBirthday.text?.isEmpty() == true) {
            isValid = false
            binding.editTextBirthday.error = "Birthday is Empty"
        } else {
            binding.editTextBirthday.error = null
        }
        if (binding.editTextAddress.text?.isEmpty() == true) {
            isValid = false
            binding.editTextAddress.error = "Address is Empty"
        } else {
            binding.editTextAddress.error = null
        }
        if (binding.editTextEmail.text?.isEmpty() == true) {
            isValid = false
            binding.editTextEmail.error = "Email is Empty"
        } else {
            binding.editTextEmail.error = null
        }
        if (binding.editTextGender.text?.isEmpty() == true) {
            isValid = false
            binding.editTextGender.error = "Gender is Empty"
        } else {
            binding.editTextGender.error = null
        }
        if (binding.editTextMobile.text?.isEmpty() == true) {
            isValid = false
            binding.editTextMobile.error = "Mobile Number is Empty"
        } else {
            binding.editTextMobile.error = null
        }
        return isValid
    }

    private fun initListener() {
        binding.buttonAdd.setOnClickListener {
            if (infoValid()) {
                val body = getInfoPatient()
                viewModel.addPatient(body)
            }
        }
    }

    private fun getInfoPatient(): AddPatientRequest {
        return AddPatientRequest(
            binding.editTextFullName.text.toString(),
            binding.editTextAddress.text.toString(),
            binding.editTextGender.text.toString(),
            binding.editTextBirthday.text.toString(),
            binding.editTextMobile.text.toString(),
            binding.editTextEmail.text.toString(),
        )
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.addPatientsStateFlow.collect { response ->
                if (response != null)
                //return@collect
                    Toast.makeText(
                        requireContext(),
                        "Patient added successfully :\nname : ${response.name}\ncreatedAt : ${response.createdAt}",
                        Toast.LENGTH_SHORT
                    ).show()
            }
        }
        lifecycleScope.launch {
            viewModel.addPatientsLoadingStateFlow.collect { show ->
                binding.progressCircular.isVisible = show
            }
        }
    }
}

