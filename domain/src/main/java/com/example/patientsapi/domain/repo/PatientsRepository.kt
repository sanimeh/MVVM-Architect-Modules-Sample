package com.example.patientsapi.domain.repo

import com.example.patientsapi.domain.model.add.AddPatientRemoteModel
import com.example.patientsapi.domain.model.add.BodyAddPatientModel
import com.example.patientsapi.domain.model.delete.PatientDeleteResponseModel
import com.example.patientsapi.domain.model.patients.PatientRemoteModel

interface PatientsRepository {

    suspend fun getPatients(): List<PatientRemoteModel>

    suspend fun addPatients(bodyAddPatientModel: BodyAddPatientModel): AddPatientRemoteModel

    suspend fun daletePatients(id: String): PatientDeleteResponseModel

    suspend fun getPatientById(id: String): PatientRemoteModel

}