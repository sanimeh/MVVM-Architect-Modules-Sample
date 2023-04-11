package com.example.patientsapi.domain.usecase.add

import com.example.patientsapi.domain.model.AddPatientRemoteModel
import com.example.patientsapi.domain.model.add.BodyAddPatientModel
import com.example.patientsapi.domain.repo.PatientsRepository
import javax.inject.Inject

class AddPatientUseCase @Inject constructor(private val repository: PatientsRepository) {

    suspend operator fun invoke(bodyAddPatientModel: BodyAddPatientModel): AddPatientRemoteModel {
        return repository.addPatients(bodyAddPatientModel)
    }
}