package com.example.evops.screens.settings.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evops.R
import com.example.evops.screens.settings.domain.usecases.GetLanguageCodeUseCase
import com.example.evops.screens.settings.domain.usecases.GetUserNameUseCase
import com.example.evops.screens.settings.domain.usecases.LogoutUseCase
import com.example.evops.screens.settings.domain.usecases.UpdateLanguageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SettingsViewModel
@Inject
constructor(
    private val updateLanguageUseCase: UpdateLanguageUseCase,
    private val getLanguageCodeUseCase: GetLanguageCodeUseCase,
    private val getUserNameUseCase: GetUserNameUseCase,
    private val logoutUseCase: LogoutUseCase,
    @ApplicationContext context: Context,
) : ViewModel() {
    private val _settingsState = MutableStateFlow(SettingsState())
    val settingsState = _settingsState

    init {
        viewModelScope.launch {
            val userName = getUserNameUseCase()
            getLanguageCodeUseCase().collect { languageCode ->
                _settingsState.update { currentState ->
                    currentState.copy(
                        languageCode = languageCode,
                        userName = userName ?: context.getString(R.string.unknown_user),
                    )
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
            is SettingsEvent.Logout -> {
                viewModelScope.launch { logoutUseCase() }
            }
        }
    }
}
