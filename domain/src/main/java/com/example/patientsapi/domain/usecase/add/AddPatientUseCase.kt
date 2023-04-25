package com.example.patientsapi.domain.usecase.add

import com.example.patientsapi.domain.model.add.AddPatientRemoteModel
import com.example.patientsapi.domain.model.add.AddPatientRequest
import com.example.patientsapi.domain.repo.PatientsRepository
import javax.inject.Inject

class AddPatientUseCase @Inject constructor(private val repository: PatientsRepository) {

    suspend operator fun invoke(addPatientRequest: AddPatientRequest): AddPatientRemoteModel {
        return repository.addPatients(addPatientRequest)
    }
}