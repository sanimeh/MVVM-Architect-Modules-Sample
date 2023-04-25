package com.example.patientsapi.domain.usecase.details

import com.example.patientsapi.domain.model.patients.PatientResponse
import com.example.patientsapi.domain.repo.PatientsRepository
import javax.inject.Inject

class GetPatientByIdUseCase@Inject constructor(private val repository: PatientsRepository) {

    suspend operator fun invoke(id: String): PatientResponse {
        return repository.getPatientById(id)
    }
}