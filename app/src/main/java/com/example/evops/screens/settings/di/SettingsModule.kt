package com.example.evops.screens.settings.di

import android.content.Context
import com.example.evops.core.common.LanguageChanger
import com.example.evops.core.data.datastore.AuthDataStore
import com.example.evops.screens.settings.data.datastore.SettingsDataStore
import com.example.evops.screens.settings.data.repository.SettingsRepositoryImpl
import com.example.evops.screens.settings.domain.repository.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {
    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): SettingsDataStore {
        return SettingsDataStore(context)
    }

    @Provides
    @Singleton
    fun provideLanguageChanger(@ApplicationContext context: Context): LanguageChanger {
        return LanguageChanger(context)
    }

    @Provides
    @Singleton
    fun provideSettingsRepository(
        dataStore: SettingsDataStore,
        languageChanger: LanguageChanger,
        authDataStore: AuthDataStore,
    ): SettingsRepository {
        return SettingsRepositoryImpl(dataStore, languageChanger, authDataStore)
    }
}
