package com.example.patientsapi.presentation.features.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patientsapi.domain.model.AddPatientRemoteModel
import com.example.patientsapi.domain.model.add.BodyAddPatientModel
import com.example.patientsapi.domain.usecase.add.AddPatientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPatientViewModel @Inject constructor(private val addPatientUseCase: AddPatientUseCase) :
    ViewModel() {

    private val _addPatientsStateFlow: MutableStateFlow<AddPatientRemoteModel?> = MutableStateFlow(null)
    val addPatientsStateFlow = _addPatientsStateFlow.asStateFlow()

    private val _addPatientsLoadingStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val addPatientsLoadingStateFlow = _addPatientsLoadingStateFlow.asStateFlow()

    private val _addPatientsErorsStateFlow: MutableStateFlow<Exception?> = MutableStateFlow(null)
    val addPatientsErorsStateFlow = _addPatientsErorsStateFlow.asStateFlow()

    fun addPatient(bodyAddPatientModel: BodyAddPatientModel){
        viewModelScope.launch {
            _addPatientsLoadingStateFlow.emit(true)
            try {
                _addPatientsStateFlow.emit(addPatientUseCase(bodyAddPatientModel))
            } catch (e: Exception) {
                _addPatientsErorsStateFlow.emit(e)
            }
            _addPatientsLoadingStateFlow.emit(false)
        }
    }
}