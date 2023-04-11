package com.example.patientsapi.data.datasource

import com.example.patientsapi.domain.model.AddPatientRemoteModel
import com.example.patientsapi.domain.model.add.BodyAddPatientModel
import com.example.patientsapi.domain.model.delete.PatientDeleteResponseModel
import com.example.patientsapi.domain.model.details.DetailsPatientWrappedRemoteModel
import com.example.patientsapi.domain.model.patients.PatientsWrapperRemoteModel
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PatientsDataSource {

    @GET("patients")
    suspend fun getPatients(): PatientsWrapperRemoteModel

    @POST("patients")
    suspend fun AddPatients(@Body bodyAddPatientModel: BodyAddPatientModel): AddPatientRemoteModel

    @DELETE("patients/{id}")
    suspend fun deletePatient(@Path("id") id: String): PatientDeleteResponseModel

    @GET("patients/{id}")
    suspend fun getPatientById(@Path("id") id: String): DetailsPatientWrappedRemoteModel


}