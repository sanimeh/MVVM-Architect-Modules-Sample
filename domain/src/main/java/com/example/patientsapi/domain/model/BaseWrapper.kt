package com.example.patientsapi.domain.model

data class BaseWrapper<T>(

    val status: Int,
    val message: String,
    val data: T
)
