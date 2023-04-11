package com.example.patientsapi.domain.usecase.delete
import com.example.patientsapi.domain.model.delete.PatientDeleteResponseModel
import com.example.patientsapi.domain.repo.PatientsRepository
import javax.inject.Inject

class DeletePatientUseCase @Inject constructor(private val repository: PatientsRepository) {

    suspend operator fun invoke(id: String): PatientDeleteResponseModel {
        return repository.daletePatients(id)
    }
}