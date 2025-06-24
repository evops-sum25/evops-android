package com.example.evops.screens.evendetails.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.evops.screens.evendetails.presentation.components.EventDetailsAttendeesButton
import com.example.evops.screens.evendetails.presentation.components.EventDetailsDescription
import com.example.evops.screens.evendetails.presentation.components.EventDetailsImagePager
import com.example.evops.screens.evendetails.presentation.components.EventDetailsPlaceAndDate
import com.example.evops.screens.evendetails.presentation.components.EventDetailsTags
import com.example.evops.screens.evendetails.presentation.components.EventDetailsTitle
import com.example.evops.screens.evendetails.presentation.components.EventDetailsTopBar
import java.time.LocalDate


@Composable
fun EventDetailsScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: EventDetailsViewModel = hiltViewModel()
) {
    val eventDetailsState by viewModel.eventDetailsState.collectAsStateWithLifecycle()

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        EventDetailsTopBar(modifier = Modifier.fillMaxWidth())
        EventDetailsImagePager(
            imageUrls = eventDetailsState.eventDetails?.eventImageUrls ?: emptyList(),
            modifier = Modifier.fillMaxWidth()
        )
        EventDetailsTitle(
            title = eventDetailsState.eventDetails?.title ?: "hui",
            modifier = Modifier.fillMaxWidth()
        )
        EventDetailsDescription(
            description = eventDetailsState.eventDetails?.description ?: "max lox",
            modifier = Modifier.fillMaxWidth()
        )
        EventDetailsTags(
            tagsData = eventDetailsState.eventDetails?.tagsData ?: emptyList(),
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
            modifier = Modifier.fillMaxWidth()
        ) {
            EventDetailsAttendeesButton(
                attendeesCount = eventDetailsState.eventDetails?.attendeesCount ?: 0u
            )
            EventDetailsPlaceAndDate(
                place = eventDetailsState.eventDetails?.place ?: "IU",
                date = eventDetailsState.eventDetails?.date ?: LocalDate.now(),
            )
        }
    }
}
