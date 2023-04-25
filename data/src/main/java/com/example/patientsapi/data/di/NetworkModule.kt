package com.example.patientsapi.data.di

import com.example.patientsapi.data.datasource.PatientsDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    const val baseUrl = "https://patients-app-api.herokuapp.com"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun providePatientDataSource(retrofit: Retrofit): PatientsDataSource {
        return retrofit.create(PatientsDataSource::class.java)
    }

//    but INJECT CONSTRCTOR make this not necessary
//    fun provideRepo(dataSource: PatientsDataSource): PatientsRepository {
//        return PatientsRepository(dataSource)
//    }
}