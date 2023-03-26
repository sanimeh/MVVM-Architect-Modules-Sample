package com.example.patientsapi.domain.model.patients

data class PatientsWrapperRemoteModel(
    val status: Int,
    val message: String,
    val data: List<PatientRemoteModel>
)
