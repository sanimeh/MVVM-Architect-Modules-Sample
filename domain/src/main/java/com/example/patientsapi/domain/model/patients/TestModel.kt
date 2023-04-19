package com.example.patientsapi.domain.model.patients

import com.google.gson.annotations.SerializedName

data class TestModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("reading")
    val reading: String,
    @SerializedName("date")
    val date: String

)
