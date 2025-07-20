package com.example.evops.screens.auth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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
import com.example.evops.screens.auth.presentation.components.LoginButton
import com.example.evops.screens.auth.presentation.components.LoginTextField
import com.example.evops.screens.auth.presentation.components.PasswordTextField
import com.example.evops.screens.auth.presentation.components.SignUpButton

@Composable
fun AuthScreen(modifier: Modifier = Modifier, viewModel: AuthViewModel = hiltViewModel()) {
    val state by viewModel.authState.collectAsState()
    Scaffold(
        topBar = { TitledTopBar(title = stringResource(R.string.authorization)) },
        modifier = modifier,
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(innerPadding).padding(horizontal = 48.dp).fillMaxSize(),
        ) {
            LoginTextField(
                login = state.login,
                onEvent = viewModel::onEvent,
                modifier = Modifier.fillMaxWidth(),
            )
            PasswordTextField(
                password = state.password,
                onEvent = viewModel::onEvent,
                modifier = Modifier.fillMaxWidth(),
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                SignUpButton(onEvent = viewModel::onEvent)
                LoginButton(onEvent = viewModel::onEvent)
            }
        }
    }
}
