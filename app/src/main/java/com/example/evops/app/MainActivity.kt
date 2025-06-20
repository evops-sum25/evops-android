package com.example.evops.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.evops.core.presentation.EvOpsTheme
import com.example.evops.screens.eventlist.presentation.EventListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EvOpsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EventListScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
