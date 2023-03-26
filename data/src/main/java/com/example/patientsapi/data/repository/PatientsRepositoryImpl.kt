package com.example.patientsapi.data.repository

import com.example.patientsapi.data.datasource.PatientsDataSource
import com.example.patientsapi.domain.model.patients.PatientRemoteModel
import com.example.patientsapi.domain.repo.PatientsRepository
import javax.inject.Inject

class PatientsRepositoryImpl @Inject constructor(private val patientsDataSource: PatientsDataSource) : PatientsRepository {


   override suspend fun getPatients(): List<PatientRemoteModel> {
        val listSorted = patientsDataSource.getPatients().data.sortedBy { it.namePatient }
        return listSorted

    }


}
