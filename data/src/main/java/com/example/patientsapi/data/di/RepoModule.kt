package com.example.patientsapi.data.di

import com.example.patientsapi.data.datasource.PatientsDataSource
import com.example.patientsapi.data.repository.PatientsRepositoryImpl
import com.example.patientsapi.domain.repo.PatientsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Provides
    fun provideRepositoryPatients(patientsDataSource: PatientsDataSource): PatientsRepository {
        return PatientsRepositoryImpl(patientsDataSource)
    }

}