package com.example.patientsapi.domain.model.patients

import com.google.gson.annotations.SerializedName

data class PatientRemoteModel(
    @SerializedName("condition")
    val condition: String,

    @SerializedName("_id")
    val id: String,

    @SerializedName("name")
    val namePatient: String,

    @SerializedName("address")
    val address: String,

    @SerializedName("mobile")
    val mobile: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("birthday")
    val birthday: String,

    @SerializedName("gender")
    val gender: String,

    @SerializedName("photo")
    val photo: String,

    val test:List<TestModel>,

    var selected: Boolean = false

    )
