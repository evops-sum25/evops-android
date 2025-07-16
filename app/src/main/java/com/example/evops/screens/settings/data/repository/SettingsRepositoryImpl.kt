package com.example.evops.screens.settings.data.repository

import android.content.Context
import com.example.evops.core.common.LanguageChanger
import com.example.evops.screens.settings.data.datastore.SettingsDataStore
import com.example.evops.screens.settings.domain.repository.SettingsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SettingsRepositoryImpl
@Inject
constructor(
    @ApplicationContext private val context: Context,
    private val dataStore: SettingsDataStore,
    private val languageChanger: LanguageChanger,
) : SettingsRepository {
    override suspend fun updateLanguage(languageCode: String) {
        dataStore.updateLanguageCode(languageCode)
        languageChanger.changeLanguage(context, languageCode)
    }
}
