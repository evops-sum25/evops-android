package com.example.evops.screens.settings.presentation

sealed interface SettingsEvent {
    data class ChangeLanguage(val languageCode: String) : SettingsEvent

    data object Logout : SettingsEvent
}
