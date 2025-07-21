package com.example.evops.screens.settings.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun updateLanguage(languageCode: String): Flow<String>

    suspend fun getLanguageCode(): Flow<String>

    suspend fun logout()
}
