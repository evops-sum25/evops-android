package com.example.evops.screens.settings.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsDataStore @Inject constructor(@ApplicationContext private val context: Context) {
    val Context.datastore: DataStore<Preferences> by preferencesDataStore(PREFERENCES_NAME)

    val languageCode = context.datastore.data.map { preferences -> preferences[LANGUAGE_CODE_KEY] }

    suspend fun updateLanguageCode(languageCode: String) {
        context.datastore.edit { settings -> settings[LANGUAGE_CODE_KEY] = languageCode }
    }

    private companion object {
        const val PREFERENCES_NAME = "Settings"
        val LANGUAGE_CODE_KEY = stringPreferencesKey("language_code")
    }
}
