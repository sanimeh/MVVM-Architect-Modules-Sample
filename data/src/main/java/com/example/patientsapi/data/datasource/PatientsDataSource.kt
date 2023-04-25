package com.example.patientsapi.data.datasource

import com.example.patientsapi.domain.model.add.AddPatientRemoteModel
import com.example.patientsapi.domain.model.BaseWrapper
import com.example.patientsapi.domain.model.add.AddPatientRequest
import com.example.patientsapi.domain.model.delete.PatientDeleteResponse
import com.example.patientsapi.domain.model.patients.PatientResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PatientsDataSource {

    @GET("patients")
    suspend fun getPatients(): BaseWrapper<List<PatientResponse>>

    @POST("patients")
    suspend fun AddPatient(@Body AddPatientRequest: AddPatientRequest): AddPatientRemoteModel

    @DELETE("patients/{id}")
    suspend fun deletePatient(@Path("id") id: String): PatientDeleteResponse

    @GET("patients/{id}")
    suspend fun getPatient(@Path("id") id: String): BaseWrapper<PatientResponse>


}