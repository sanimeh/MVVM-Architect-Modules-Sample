package com.example.patientsapi.data.datasource

import com.example.patientsapi.domain.model.patients.PatientsWrapperRemoteModel
import retrofit2.http.GET

interface PatientsDataSource {

    @GET("patients")
    suspend fun getPatients(): PatientsWrapperRemoteModel

}