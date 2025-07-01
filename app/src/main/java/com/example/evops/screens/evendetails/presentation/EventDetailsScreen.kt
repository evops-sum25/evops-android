package com.example.evops.screens.evendetails.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.evops.screens.evendetails.presentation.components.EventDetailsImagePager
import com.example.evops.screens.evendetails.presentation.components.EventDetailsTopBar

@Composable
fun EventDetailsScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: EventDetailsViewModel = hiltViewModel(),
) {
    val eventDetailsState by viewModel.eventDetailsState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { EventDetailsTopBar(navController = navController) },
        modifier = modifier,
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier =
                Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .padding(innerPadding),
        ) {
            eventDetailsState.eventDetails?.let { eventDetails ->
                EventDetailsImagePager(
                    imageUrls = eventDetails.imageUrls,
                    modifier = Modifier.fillMaxWidth(),
                )
                EventDetailsScreenContent(
                    eventDetails = eventDetails,
                    modifier =
                        Modifier
                            .padding(horizontal = 18.dp, vertical = 12.dp)
                            .fillMaxSize(),
                )
            }
        }
    }
}
