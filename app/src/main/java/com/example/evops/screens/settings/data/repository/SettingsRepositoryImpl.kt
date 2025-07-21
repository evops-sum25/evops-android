package com.example.evops.screens.settings.data.repository

import com.example.evops.core.common.Config
import com.example.evops.core.common.LanguageChanger
import com.example.evops.core.data.datastore.AuthDataStore
import com.example.evops.core.data.datastore.AuthState
import com.example.evops.core.domain.repository.AuthRepository
import com.example.evops.screens.settings.data.SettingsApi
import com.example.evops.screens.settings.data.datastore.SettingsDataStore
import com.example.evops.screens.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class SettingsRepositoryImpl
@Inject
constructor(
    private val settingsApi: SettingsApi,
    private val settingsDataStore: SettingsDataStore,
    private val languageChanger: LanguageChanger,
    private val authDataStore: AuthDataStore,
    private val authRepository: AuthRepository,
) : SettingsRepository {
    override suspend fun updateLanguage(languageCode: String): Flow<String> {
        settingsDataStore.updateLanguageCode(languageCode)
        languageChanger.changeLanguage(languageCode)
        return getLanguageCode()
    }

    override suspend fun getLanguageCode(): Flow<String> = flow {
        settingsDataStore.languageCode.collect { languageCode ->
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

    override suspend fun getUserName(): String? {
        return intercept { token -> settingsApi.getUserName(Config.constructAccessToken(token)) }
            ?.user
            ?.displayName
    }

    private suspend fun <T> intercept(base: suspend (String) -> Response<T>): T? {
        val token = authDataStore.accessToken.filterNotNull().first()
        return token.let {
            val response = base(it)
            if (response.code() == 401) {
                authRepository.refresh()
                val refreshedToken = authDataStore.accessToken.filterNotNull().first()
                base(refreshedToken).body()
            } else {
                response.body()
            }
        }
    }
}
