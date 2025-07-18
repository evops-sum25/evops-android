package com.example.evops.screens.settings.domain.usecases

import com.example.evops.screens.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateLanguageUseCase
@Inject
constructor(private val settingsRepository: SettingsRepository) {
    suspend operator fun invoke(languageCode: String): Flow<String> {
        return settingsRepository.updateLanguage(languageCode)
    }
}
