package com.example.evops.screens.createevent.di

import com.example.evops.screens.createevent.data.CreateEventNetworkRepositoryImpl
import com.example.evops.screens.createevent.data.api.CreateEventApi
import com.example.evops.screens.createevent.domain.repositories.CreateEventNetworkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CreateEventNetworkModule {
    @Provides
    @Singleton
    fun provideCreateEventApi(retrofit: Retrofit): CreateEventApi {
        return retrofit.create(CreateEventApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCreateEventRepository(api: CreateEventApi): CreateEventNetworkRepository {
        return CreateEventNetworkRepositoryImpl(api)
    }
}
