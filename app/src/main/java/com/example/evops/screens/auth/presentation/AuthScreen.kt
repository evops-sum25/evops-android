package com.example.evops.screens.auth.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.evops.R
import com.example.evops.core.presentation.components.topbar.TitledTopBar
import com.example.evops.screens.auth.presentation.components.LoginScreenContent
import com.example.evops.screens.auth.presentation.components.SignUpScreenContent
import com.example.evops.screens.auth.presentation.state.AuthScreen

@Composable
fun AuthScreen(modifier: Modifier = Modifier, viewModel: AuthViewModel = hiltViewModel()) {
    val state by viewModel.authState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.snackbarMessage) {
        state.snackbarMessage?.let { message ->
            snackbarHostState.showSnackbar(message = message, duration = SnackbarDuration.Short)
            viewModel.onEvent(AuthEvent.HideSnackbar)
        }
    }
    Scaffold(
        topBar = { TitledTopBar(title = stringResource(R.string.authorization)) },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        modifier = modifier,
    ) { innerPadding ->
        when (state.currentScreen) {
            AuthScreen.SIGN_UP -> {
                SignUpScreenContent(
                    state = state,
                    onEvent = viewModel::onEvent,
                    modifier = Modifier.padding(innerPadding),
                )
            }
            AuthScreen.LOGIN -> {
                LoginScreenContent(
                    state = state,
                    onEvent = viewModel::onEvent,
                    modifier = Modifier.padding(innerPadding),
                )
            }
        }
    }
}
