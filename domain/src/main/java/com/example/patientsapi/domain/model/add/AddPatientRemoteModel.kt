package com.example.patientsapi.domain.model.add

import com.google.gson.annotations.SerializedName

data class AddPatientRemoteModel(
    @SerializedName("condition")
    val condition: String,
    @SerializedName("_id")
    val _id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("mobile")
    val mobile: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("birthdate")
    val birthdate: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("__v")
    val __v: Int,
)
