package com.example.evops.screens.settings.domain.usecases

import com.example.evops.screens.settings.domain.repository.SettingsRepository
import javax.inject.Inject

class UpdateLanguageUseCase
@Inject
constructor(private val settingsRepository: SettingsRepository) {
    suspend operator fun invoke(languageCode: String) {
        settingsRepository.updateLanguage(languageCode)
    }
}
