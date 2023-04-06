package com.example.patientsapi.data.datasource

import com.example.patientsapi.domain.model.AddPatientsRemoteModel
import com.example.patientsapi.domain.model.add.BodyAddPatientModel
import com.example.patientsapi.domain.model.patients.PatientsWrapperRemoteModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PatientsDataSource {

    @GET("patients")
    suspend fun getPatients(): PatientsWrapperRemoteModel

    @POST("patients")
    suspend fun AddPatients(@Body bodyAddPatientModel: BodyAddPatientModel): AddPatientsRemoteModel
}