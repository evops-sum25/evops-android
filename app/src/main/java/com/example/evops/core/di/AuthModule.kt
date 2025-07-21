package com.example.evops.core.di

import android.content.Context
import com.example.evops.core.data.network.api.AuthApi
import com.example.evops.core.data.datastore.AuthDataStore
import com.example.evops.screens.auth.data.repository.AuthRepositoryImpl
import com.example.evops.core.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): AuthDataStore {
        return AuthDataStore(context)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi, dataStore: AuthDataStore): AuthRepository {
        return AuthRepositoryImpl(api, dataStore)
    }
}
