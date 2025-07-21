package com.example.evops.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
            val startDestination =
                if (authState == AuthState.NEED_LOGIN.string) SubGraph.Auth else SubGraph.Home

            EvOpsTheme {
                Scaffold(
                    bottomBar = { EvopsNavigationBar(navController = navController) },
                    modifier = Modifier.fillMaxSize(),
                    contentWindowInsets = WindowInsets(0.dp),
                ) { innerPadding ->
                    EvopsNavGraph(
                        startDestination = startDestination,
                        navController = navController,
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
            LaunchedEffect(authState) {
                if (authState == AuthState.NEED_LOGIN.string) {
                    try {
                        navController.navigate(SubGraph.Auth) { popUpTo(0) { inclusive = true } }
                    } catch (_: IllegalStateException) {}
                }
            }
        }
    }
}
