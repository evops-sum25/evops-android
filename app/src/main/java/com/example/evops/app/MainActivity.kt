package com.example.evops.app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.evops.core.presentation.EvOpsTheme
import com.example.evops.screens.PreviewData
import com.example.evops.screens.eventlist.data.dto.ExampleDto
import com.example.evops.screens.eventlist.domain.repository.ExampleRepository
import com.example.evops.screens.eventlist.presentation.components.PreviewData
import com.example.evops.screens.eventlist.presentation.components.eventcard.EventCard
import com.example.evops.screens.eventlist.presentation.components.searchfield.SearchField
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var repository: ExampleRepository

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
        lifecycleScope.launch {
            try {
                val result = repository.createEvent(
                    ExampleDto(
                        description = "max lox",
                        name = "lol"
                    )
                )
                Log.d("DEBUG API", "name: ${result.name}, description: ${result.description}")
            } catch (e: Exception) {
                Log.e("DEBUG API", "Create event failed", e)
            }
        }
    }
}
