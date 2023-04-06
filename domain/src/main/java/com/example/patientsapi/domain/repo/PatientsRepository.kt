package com.example.patientsapi.domain.repo

import com.example.patientsapi.domain.model.AddPatientsRemoteModel
import com.example.patientsapi.domain.model.add.BodyAddPatientModel
import com.example.patientsapi.domain.model.patients.PatientRemoteModel

interface PatientsRepository {

    suspend fun getPatients(): List<PatientRemoteModel>

    suspend fun addPatients(bodyAddPatientModel: BodyAddPatientModel): AddPatientsRemoteModel
}