package com.example.evops.screens.settings.data.repository

import com.example.evops.core.common.LanguageChanger
import com.example.evops.core.data.datastore.AuthDataStore
import com.example.evops.core.data.datastore.AuthState
import com.example.evops.screens.settings.data.datastore.SettingsDataStore
import com.example.evops.screens.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SettingsRepositoryImpl
@Inject
constructor(
    private val dataStore: SettingsDataStore,
    private val languageChanger: LanguageChanger,
    private val authDataStore: AuthDataStore,
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

    override suspend fun logout() {
        authDataStore.updateAuthState(AuthState.NEED_LOGIN)
    }
}
