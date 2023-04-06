package com.example.patientsapi.domain.usecase.add

import com.example.patientsapi.domain.model.AddPatientsRemoteModel
import com.example.patientsapi.domain.model.add.BodyAddPatientModel
import com.example.patientsapi.domain.repo.PatientsRepository
import javax.inject.Inject

class AddPatientUseCase @Inject constructor(private val repository: PatientsRepository) {

    suspend operator fun invoke(bodyAddPatientModel: BodyAddPatientModel): AddPatientsRemoteModel {
        return repository.addPatients(bodyAddPatientModel)
    }
}