package com.example.evops.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.evops.core.presentation.EvOpsTheme
import com.example.evops.screens.eventlist.presentation.components.PreviewData
import com.example.evops.screens.eventlist.presentation.components.eventcard.EventCard
import com.example.evops.screens.eventlist.presentation.components.searchfield.SearchField

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EvOpsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        SearchField(
                            modifier = Modifier.padding(
                                horizontal = 18.dp,
                                vertical = 4.dp
                            )
                        )

                        EventCard(
                            eventData = PreviewData.eventData,
                            modifier = Modifier
                        )
                    }
                }
            }
        }
    }
}
