package com.example.patientsapi.domain.usecase.delete
import com.example.patientsapi.domain.model.delete.PatientDeleteResponse
import com.example.patientsapi.domain.repo.PatientsRepository
import javax.inject.Inject

class DeletePatientUseCase @Inject constructor(private val repository: PatientsRepository) {

    suspend operator fun invoke(id: String): PatientDeleteResponse {
        return repository.daletePatients(id)
    }
}