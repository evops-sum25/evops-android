package com.example.evops.screens.evendetails.di

import com.example.evops.screens.evendetails.data.api.EventDetailsApi
import com.example.evops.screens.evendetails.data.repositories.EventDetailsNetworkRepositoryImpl
import com.example.evops.screens.evendetails.domain.repositories.EventDetailsNetworkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EventDetailsNetworkModule {
    @Provides
    @Singleton
    fun provideEventDetailsApi(retrofit: Retrofit): EventDetailsApi {
        return retrofit.create(EventDetailsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideEventDetailsRepository(api: EventDetailsApi): EventDetailsNetworkRepository {
        return EventDetailsNetworkRepositoryImpl(api)
    }
}
