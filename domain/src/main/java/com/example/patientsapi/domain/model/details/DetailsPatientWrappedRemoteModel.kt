package com.example.patientsapi.domain.model.details

import com.example.patientsapi.domain.model.patients.PatientRemoteModel

data class DetailsPatientWrappedRemoteModel(
    val status: Int,
    val message: String,
    val date: PatientRemoteModel
)