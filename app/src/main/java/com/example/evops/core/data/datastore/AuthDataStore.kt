package com.example.evops.core.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthDataStore @Inject constructor(@ApplicationContext private val context: Context) {
    val Context.datastore: DataStore<Preferences> by preferencesDataStore(PREFERENCES_NAME)

    val accessToken = context.datastore.data.map { preferences -> preferences[ACCESS_TOKEN_KEY] }
    val refreshToken = context.datastore.data.map { preferences -> preferences[REFRESH_TOKEN_KEY] }
    val authState = context.datastore.data.map { preferences -> preferences[AUTH_STATE_KEY] }

    suspend fun updateAccessToken(accessToken: String) {
        context.datastore.edit { authTokens -> authTokens[ACCESS_TOKEN_KEY] = accessToken }
    }

    suspend fun updateRefreshToken(refreshToken: String) {
        context.datastore.edit { authTokens -> authTokens[REFRESH_TOKEN_KEY] = refreshToken }
    }

    suspend fun updateAuthState(authState: AuthState) {
        context.datastore.edit { authTokens -> authTokens[AUTH_STATE_KEY] = authState.string }
    }

    private companion object {
        const val PREFERENCES_NAME = "Auth"
        val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")
        val AUTH_STATE_KEY = stringPreferencesKey("auth_state")
    }
}
