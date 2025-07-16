package com.example.evops.screens.settings.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.evops.R
import com.example.evops.core.presentation.components.topbar.TitledTopBar

@Composable
fun SettingsScreen(modifier: Modifier = Modifier, viewModel: SettingsViewModel = hiltViewModel()) {
    val state by viewModel.settingsState.collectAsState()
    Scaffold(
        topBar = { TitledTopBar(title = stringResource(R.string.settings)) },
        modifier = modifier,
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(state.languageCode)
            val newLangCode = if (state.languageCode == "ru") "en" else "ru"
            Button(onClick = { viewModel.onEvent(SettingsEvent.ChangeLanguage(newLangCode)) }) {
                Text(stringResource(R.string.change_language))
            }
        }
    }
}
