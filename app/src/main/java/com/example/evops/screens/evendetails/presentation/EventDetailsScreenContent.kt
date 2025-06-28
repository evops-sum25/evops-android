package com.example.evops.screens.evendetails.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.evops.screens.evendetails.presentation.components.EventDetailsAttendeesButton
import com.example.evops.screens.evendetails.presentation.components.EventDetailsDescription
import com.example.evops.screens.evendetails.presentation.components.EventDetailsPlaceAndDate
import com.example.evops.screens.evendetails.presentation.components.EventDetailsTags
import com.example.evops.screens.evendetails.presentation.components.EventDetailsTitle
import java.time.LocalDate

@Composable
fun EventDetailsScreenContent(
    eventDetailsState: EventDetailsState,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        EventDetailsTitle(
            title = eventDetailsState.eventDetails?.title ?: "Default Title",
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
        )
        EventDetailsDescription(
            description = eventDetailsState.eventDetails?.description ?: "Default Description",
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
        )
        EventDetailsTags(
            tagsData = eventDetailsState.eventDetails?.tagsData ?: emptyList(),
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
            modifier = Modifier.fillMaxWidth(),
        ) {
            EventDetailsAttendeesButton(
                attendeesCount = eventDetailsState.eventDetails?.attendeesCount ?: 0u,
            )
            EventDetailsPlaceAndDate(
                place = eventDetailsState.eventDetails?.place ?: "IU",
                date = eventDetailsState.eventDetails?.date ?: LocalDate.now(),
            )
        }
    }
}
