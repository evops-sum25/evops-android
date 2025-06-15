package com.example.evops.screens.eventlist.di

import com.example.evops.screens.eventlist.data.api.ExampleApi
import com.example.evops.screens.eventlist.data.ExampleRepositoryImpl
import com.example.evops.screens.eventlist.domain.repository.ExampleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ExampleModule {

    @Provides
    @Singleton
    fun provideExampleApi(retrofit: Retrofit): ExampleApi {
        return retrofit.create(ExampleApi::class.java)
    }

    @Provides
    @Singleton
    fun provideExampleRepository(api: ExampleApi): ExampleRepository {
        return ExampleRepositoryImpl(api)
    }
}