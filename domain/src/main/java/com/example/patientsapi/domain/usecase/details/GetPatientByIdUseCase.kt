package com.example.patientsapi.domain.usecase.details

import com.example.patientsapi.domain.model.delete.PatientDeleteResponseModel
import com.example.patientsapi.domain.model.patients.PatientRemoteModel
import com.example.patientsapi.domain.repo.PatientsRepository
import javax.inject.Inject

class GetPatientByIdUseCase@Inject constructor(private val repository: PatientsRepository) {

    suspend operator fun invoke(id: String): PatientRemoteModel {
        return repository.getPatientById(id)
    }
}