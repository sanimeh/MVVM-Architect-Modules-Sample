package com.example.patientsapi.domain.usecase.patients

import com.example.patientsapi.domain.model.patients.PatientResponse
import com.example.patientsapi.domain.repo.PatientsRepository
import javax.inject.Inject

class GetPatientsSortedByNameUseCase @Inject constructor(private val repository: PatientsRepository) {
    suspend operator fun invoke(): List<PatientResponse> {
        return repository.getPatients().sortedBy { it.namePatient }
    }
}