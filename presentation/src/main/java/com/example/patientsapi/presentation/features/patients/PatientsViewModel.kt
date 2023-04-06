package com.example.patientsapi.presentation.features.patients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patientsapi.domain.repo.PatientsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class  PatientsViewModel @Inject constructor(val repository: PatientsRepository) : ViewModel() {

    private val _patientsStateFlow: MutableStateFlow<List<com.example.patientsapi.domain.model.patients.PatientRemoteModel>> =
        MutableStateFlow(emptyList())
    val patientsStateFlow = _patientsStateFlow.asStateFlow()

    private val _patientsLoadingStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val patientsLoadingStateFlow = _patientsLoadingStateFlow.asStateFlow()

    private val _patientsErorsStateFlow: MutableStateFlow<Exception?> = MutableStateFlow(null)
    val patientsErorsStateFlow = _patientsErorsStateFlow.asStateFlow()

    init {
        getPatients()
    }

    private fun getPatients() {
        viewModelScope.launch {
            _patientsLoadingStateFlow.emit(false)
            try {
                _patientsStateFlow.emit(repository.getPatients())
            } catch (e: Exception) {
                _patientsErorsStateFlow.emit(e)
            }
            _patientsLoadingStateFlow.emit(false)
        }
    }
}