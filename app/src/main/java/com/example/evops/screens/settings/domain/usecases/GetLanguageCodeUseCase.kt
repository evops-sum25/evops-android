package com.example.evops.screens.settings.domain.usecases

import com.example.evops.screens.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLanguageCodeUseCase
@Inject
constructor(private val settingsRepository: SettingsRepository) {
    suspend operator fun invoke(): Flow<String> {
        return settingsRepository.getLanguageCode()
    }
}
