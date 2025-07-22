package com.example.evops.core.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.flow.map

private val Context.authDataStore: DataStore<AuthPreferences> by
    dataStore(fileName = AuthDataStore.PREFERENCES_NAME, serializer = AuthPreferencesSerializer)

class AuthDataStore @Inject constructor(@ApplicationContext private val context: Context) {
    private val dataStore = context.authDataStore

    val authPreferencesFlow = dataStore.data

    val accessToken = authPreferencesFlow.map { it.accessToken }
    val refreshToken = authPreferencesFlow.map { it.refreshToken }
    val authState = authPreferencesFlow.map { it.authState }

    suspend fun updateAccessToken(accessToken: String?) {
        dataStore.updateData { prefs -> prefs.copy(accessToken = accessToken) }
    }

    suspend fun updateRefreshToken(refreshToken: String?) {
        dataStore.updateData { prefs -> prefs.copy(refreshToken = refreshToken) }
    }

    suspend fun updateAuthState(authState: AuthState) {
        dataStore.updateData { prefs -> prefs.copy(authState = authState.string) }
    }

    companion object {
        const val PREFERENCES_NAME = "auth_prefs.pb"
    }
}
