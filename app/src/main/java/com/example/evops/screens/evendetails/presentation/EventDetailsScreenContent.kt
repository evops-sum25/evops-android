package com.example.evops.screens.evendetails.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.evops.screens.evendetails.domain.model.EventDetails
import com.example.evops.screens.evendetails.presentation.components.EventDetailsAttendeesButton
import com.example.evops.screens.evendetails.presentation.components.EventDetailsDescription
import com.example.evops.screens.evendetails.presentation.components.EventDetailsPlaceAndDate
import com.example.evops.screens.evendetails.presentation.components.EventDetailsTags
import com.example.evops.screens.evendetails.presentation.components.EventDetailsTitleAndAuthor

@Composable
fun EventDetailsScreenContent(
    eventDetails: EventDetails,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier,
    ) {
        EventDetailsTitleAndAuthor(
            title = eventDetails.title,
            author = eventDetails.author,
        )

        EventDetailsDescription(
            description = eventDetails.description,
        )

        EventDetailsTags(
            tagsData = eventDetails.tagsData,
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
            modifier = Modifier.fillMaxWidth(),
        ) {
            EventDetailsAttendeesButton(
                attendeesCount = eventDetails.attendeesCount,
            )

            EventDetailsPlaceAndDate(
                place = eventDetails.place,
                date = eventDetails.date,
            )
        }
    }
}
