package com.example.patientsapi.domain.repo

import com.example.patientsapi.domain.model.patients.PatientRemoteModel

interface PatientsRepository {

    suspend fun getPatients(): List<PatientRemoteModel>
}