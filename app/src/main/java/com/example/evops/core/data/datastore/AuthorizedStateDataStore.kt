package com.example.evops.core.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.flow.map

class AuthorizedStateDataStore @Inject constructor(@ApplicationContext private val context: Context) {
    val Context.datastore: DataStore<Preferences> by preferencesDataStore(PREFERENCES_NAME)

    val accessToken = context.datastore.data.map { preferences -> preferences[ACCESS_TOKEN_KEY] }
    val refreshToken = context.datastore.data.map { preferences -> preferences[REFRESH_TOKEN_KEY] }

    suspend fun updateAccessToken(accessToken: String) {
        context.datastore.edit { authTokens -> authTokens[ACCESS_TOKEN_KEY] = accessToken }
    }

    suspend fun updateRefreshToken(refreshToken: String) {
        context.datastore.edit { authTokens -> authTokens[REFRESH_TOKEN_KEY] = refreshToken }
    }

    private companion object {
        const val PREFERENCES_NAME = "AuthState"
        val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")
    }
}
