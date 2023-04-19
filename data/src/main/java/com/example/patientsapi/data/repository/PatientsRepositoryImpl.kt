package com.example.patientsapi.data.repository

import com.example.patientsapi.data.datasource.PatientsDataSource
import com.example.patientsapi.domain.model.add.AddPatientRemoteModel
import com.example.patientsapi.domain.model.add.AddPatientRequest
import com.example.patientsapi.domain.model.delete.PatientDeleteResponse
import com.example.patientsapi.domain.model.patients.PatientResponse
import com.example.patientsapi.domain.repo.PatientsRepository
import javax.inject.Inject

class PatientsRepositoryImpl @Inject constructor(private val patientsDataSource: PatientsDataSource) :
    PatientsRepository {


    override suspend fun getPatients(): List<PatientResponse> {
        val listSorted = patientsDataSource.getPatients().data.sortedBy { it.namePatient }
        return listSorted
    }

    override suspend fun addPatients(addPatientRequest: AddPatientRequest): AddPatientRemoteModel {
        return patientsDataSource.AddPatient(addPatientRequest)
    }

    override suspend fun daletePatients(id: String): PatientDeleteResponse {
        return patientsDataSource.deletePatient(id)
    }

    override suspend fun getPatientById(id: String): PatientResponse {
        return patientsDataSource.getPatient(id).data
    }

}
