package com.example.evops.screens.eventlist.di

import com.example.evops.screens.eventlist.data.api.EventListApi
import com.example.evops.screens.eventlist.data.EventListNetworkRepositoryImpl
import com.example.evops.screens.eventlist.domain.repository.EventListNetworkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EventListNetworkModule {

    @Provides
    @Singleton
    fun provideEventListApi(retrofit: Retrofit): EventListApi {
        return retrofit.create(EventListApi::class.java)
    }

    @Provides
    @Singleton
    fun provideEventListRepository(api: EventListApi): EventListNetworkRepository {
        return EventListNetworkRepositoryImpl(api)
    }
}