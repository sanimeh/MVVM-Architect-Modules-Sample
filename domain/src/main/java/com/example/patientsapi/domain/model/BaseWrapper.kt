package com.example.patientsapi.domain.model

import com.example.patientsapi.domain.model.patients.PatientRemoteModel

data class BaseWrapper<T>(
    val status: Int,
    val message: String,
    val data: T
)
