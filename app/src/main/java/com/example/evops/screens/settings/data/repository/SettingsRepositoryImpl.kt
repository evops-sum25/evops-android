package com.example.evops.screens.settings.data.repository

import android.content.Context
import com.example.evops.core.common.LanguageChanger
import com.example.evops.screens.settings.data.datastore.SettingsDataStore
import com.example.evops.screens.settings.domain.repository.SettingsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SettingsRepositoryImpl
@Inject
constructor(
    @ApplicationContext private val context: Context,
    private val dataStore: SettingsDataStore,
    private val languageChanger: LanguageChanger,
) : SettingsRepository {
    override suspend fun updateLanguage(languageCode: String): Flow<String> {
        dataStore.updateLanguageCode(languageCode)
        languageChanger.changeLanguage(languageCode)
        return getLanguageCode()
    }

    override suspend fun getLanguageCode(): Flow<String> = flow {
        dataStore.languageCode.collect { languageCode ->
            if (languageCode == null) {
                emit(languageChanger.getLanguageCode())
            } else {
                emit(languageCode)
            }
        }
    }
}
