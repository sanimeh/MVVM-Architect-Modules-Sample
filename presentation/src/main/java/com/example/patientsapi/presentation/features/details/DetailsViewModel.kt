package com.example.patientsapi.presentation.features.details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patientsapi.domain.model.AddPatientRemoteModel
import com.example.patientsapi.domain.model.patients.PatientRemoteModel
import com.example.patientsapi.domain.usecase.details.GetPatientByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val getPatientByIdUseCase: GetPatientByIdUseCase,
    state: SavedStateHandle
) :
    ViewModel() {

    private val _detailsStateFlow: MutableStateFlow<PatientRemoteModel?> = MutableStateFlow(null)
    val detailssStateFlow = _detailsStateFlow.asStateFlow()

    private val _detailsLoadingStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val detailsLoadingStateFlow = _detailsLoadingStateFlow.asStateFlow()

    private val _detailsErorsStateFlow: MutableStateFlow<Exception?> = MutableStateFlow(null)
    val detailsErorsStateFlow = _detailsErorsStateFlow.asStateFlow()

    private val savedStateHandle = state

    init {
        details()
    }

      fun details() {
        val id = savedStateHandle.get<String>("id") ?: "-1"
        //Log.d("TAG", " id = $id")
        viewModelScope.launch {
            _detailsLoadingStateFlow.emit(true)
            try {
                _detailsStateFlow.emit(getPatientByIdUseCase(id))
            } catch (e: Exception) {
                _detailsErorsStateFlow.emit(e)
            }
            _detailsLoadingStateFlow.emit(false)
        }
    }
}
