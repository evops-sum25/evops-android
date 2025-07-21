package com.example.evops.screens.auth.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
    Scaffold(
        topBar = { TitledTopBar(title = stringResource(R.string.authorization)) },
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
