package com.example.evops.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.evops.core.data.datastore.AuthState
import com.example.evops.core.navigation.EvopsNavGraph
import com.example.evops.core.navigation.SubGraph
import com.example.evops.core.presentation.components.navbar.EvopsNavigationBar
import com.example.evops.core.presentation.style.EvOpsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainViewModel = hiltViewModel()
            val authState by viewModel.authState.collectAsState()
            val navController = rememberNavController()
            var lastHandledAuthState by rememberSaveable { mutableStateOf<String?>(null) }

            EvOpsTheme {
                Scaffold(
                    bottomBar = { EvopsNavigationBar(navController = navController) },
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    EvopsNavGraph(
                        startDestination =
                            if (authState == AuthState.NEED_LOGIN.string) SubGraph.Auth
                            else SubGraph.Home,
                        navController = navController,
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }

            LaunchedEffect(authState) {
                if (authState != lastHandledAuthState) {
                    when {
                        (authState == AuthState.NEED_LOGIN.string) -> {
                            try {
                                navController.navigate(SubGraph.Auth) {
                                    popUpTo(0) { inclusive = true }
                                }
                                lastHandledAuthState = authState
                            } catch (_: IllegalStateException) {}
                        }
                        (authState == AuthState.AUTHORIZED.string) -> {
                            try {
                                navController.navigate(SubGraph.Home) {
                                    popUpTo(0) { inclusive = true }
                                }
                                lastHandledAuthState = authState
                            } catch (_: IllegalStateException) {}
                        }
                    }
                }
            }
        }
    }
}
