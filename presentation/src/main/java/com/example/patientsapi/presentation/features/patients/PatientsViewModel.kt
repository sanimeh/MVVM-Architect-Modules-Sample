package com.example.patientsapi.presentation.features.patients

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patientsapi.domain.model.delete.PatientDeleteResponse
import com.example.patientsapi.domain.model.patients.PatientResponse
import com.example.patientsapi.domain.usecase.delete.DeletePatientUseCase
import com.example.patientsapi.domain.usecase.patients.GetPatientsSortedByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientsViewModel @Inject constructor(
    private val getPatientsSortedByNameUseCase: GetPatientsSortedByNameUseCase,
    private val deletePatientUseCase: DeletePatientUseCase
) : ViewModel() {

    private val _patientsStateFlow: MutableStateFlow<List<PatientResponse>> =
        MutableStateFlow(emptyList())
    val patientsStateFlow = _patientsStateFlow.asStateFlow()

    private val _deletePatientLiveData: MutableLiveData<PatientDeleteResponse?> =
        MutableLiveData()
    val deletePatientLiveData: MutableLiveData<PatientDeleteResponse?> = _deletePatientLiveData


    private val _patientsLoadingStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val patientsLoadingStateFlow = _patientsLoadingStateFlow.asStateFlow()

    private val _patientsErorsStateFlow: MutableStateFlow<Exception?> = MutableStateFlow(null)
    val patientsErorsStateFlow = _patientsErorsStateFlow.asStateFlow()

    init {
        getPatients()
    }

    fun getPatients() {
        viewModelScope.launch {
            _patientsLoadingStateFlow.emit(true)
            try {
                _patientsStateFlow.emit(getPatientsSortedByNameUseCase())
            } catch (e: Exception) {
                _patientsErorsStateFlow.emit(e)
            }
            _patientsLoadingStateFlow.emit(false)
        }
    }

    fun DeletePatient(id: String) {
        viewModelScope.launch {
            _patientsLoadingStateFlow.emit(true)
            try {
                _deletePatientLiveData.postValue(deletePatientUseCase(id)!!)
            } catch (e: Exception) {
                _patientsErorsStateFlow.emit(e)
            }
            _patientsLoadingStateFlow.emit(false)
        }
    }
}