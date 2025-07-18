package com.example.evops.screens.settings.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
            HorizontalDivider(thickness = 2.dp)
            LanguageSection(
                languageCode = state.languageCode,
                onEvent = viewModel::onEvent,
                modifier = Modifier.padding(horizontal = 12.dp).fillMaxWidth(),
            )
        }
    }
}

@Composable
private fun LanguageSection(
    languageCode: String,
    onEvent: (SettingsEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val newLangCode =
        if (languageCode == stringResource(R.string.ru_lang_code))
            stringResource(R.string.en_lang_code)
        else stringResource(R.string.ru_lang_code)
    Column {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier,
        ) {
            Text(
                stringResource(R.string.current_language),
                style = MaterialTheme.typography.bodyLarge,
            )

            TextButton(onClick = { onEvent(SettingsEvent.ChangeLanguage(newLangCode)) }) {
                Text(
                    stringResource(R.string.change_language),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
        HorizontalDivider(thickness = 2.dp)
    }
}
