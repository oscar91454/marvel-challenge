package com.marvel.challenge.injector.module


import com.marvel.challenge.data.DataRepositoryImpl
import com.marvel.challenge.data.api.services.APIService
import com.marvel.challenge.domain.repository.DataRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun providesMyObjectRepository(apiService: APIService): DataRepository {
        return DataRepositoryImpl(apiService)
    }
}