package com.example.evops.screens.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evops.screens.settings.domain.usecases.GetLanguageCodeUseCase
import com.example.evops.screens.settings.domain.usecases.UpdateLanguageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel
@Inject
constructor(
    private val updateLanguageUseCase: UpdateLanguageUseCase,
    private val getLanguageCodeUseCase: GetLanguageCodeUseCase,
) : ViewModel() {
    private val _settingsState = MutableStateFlow(SettingsState())
    val settingsState = _settingsState

    init {
        viewModelScope.launch {
            getLanguageCodeUseCase().collect { languageCode ->
                _settingsState.update { currentState ->
                    currentState.copy(languageCode = languageCode)
                }
            }
        }
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.ChangeLanguage -> {
                viewModelScope.launch {
                    updateLanguageUseCase(event.languageCode).collect { languageCode ->
                        _settingsState.update { currentState ->
                            currentState.copy(languageCode = languageCode)
                        }
                    }
                }
            }
        }
    }
}
