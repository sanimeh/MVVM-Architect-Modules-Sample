package com.example.patientsapi.domain.repo

import com.example.patientsapi.domain.model.add.AddPatientRemoteModel
import com.example.patientsapi.domain.model.add.AddPatientRequest
import com.example.patientsapi.domain.model.delete.PatientDeleteResponse
import com.example.patientsapi.domain.model.patients.PatientResponse

interface PatientsRepository {

    suspend fun getPatients(): List<PatientResponse>

    suspend fun addPatients(addPatientRequest: AddPatientRequest): AddPatientRemoteModel

    suspend fun daletePatients(id: String): PatientDeleteResponse

    suspend fun getPatientById(id: String): PatientResponse

}