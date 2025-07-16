package com.example.evops.screens.settings.domain.repository

interface SettingsRepository {
    suspend fun updateLanguage(languageCode: String)
}
