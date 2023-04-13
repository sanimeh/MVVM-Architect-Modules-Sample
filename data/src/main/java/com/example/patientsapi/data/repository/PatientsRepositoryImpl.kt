package com.example.patientsapi.data.repository

import com.example.patientsapi.data.datasource.PatientsDataSource
import com.example.patientsapi.domain.model.AddPatientRemoteModel
import com.example.patientsapi.domain.model.add.BodyAddPatientModel
import com.example.patientsapi.domain.model.delete.PatientDeleteResponseModel
import com.example.patientsapi.domain.model.details.DetailsPatientWrappedRemoteModel
import com.example.patientsapi.domain.model.patients.PatientRemoteModel
import com.example.patientsapi.domain.repo.PatientsRepository
import javax.inject.Inject

class PatientsRepositoryImpl @Inject constructor(private val patientsDataSource: PatientsDataSource) :
    PatientsRepository {


    override suspend fun getPatients(): List<PatientRemoteModel> {
        val listSorted = patientsDataSource.getPatients().data.sortedBy { it.namePatient }
        return listSorted
    }

    override suspend fun addPatients(bodyAddPatientModel: BodyAddPatientModel): AddPatientRemoteModel {
        return patientsDataSource.AddPatients(bodyAddPatientModel)
    }

    override suspend fun daletePatients(id: String): PatientDeleteResponseModel {
        return patientsDataSource.deletePatient(id)
    }

    override suspend fun getPatientById(id: String): PatientRemoteModel {
        return patientsDataSource.getPatientById(id).data
    }

}
