package com.example.patientsapi.domain.model

data class AddPatientsRemoteModel(
    val condition: String,
    val _id: String,
    val name: String,
    val address: String,
    val mobile: String,
    val email: String,
    val birthdate: String,
    val gender: String,
    val photo: String,
    val createdAt: String,
    val updatedAt: String,
    val __v: Int,
)
