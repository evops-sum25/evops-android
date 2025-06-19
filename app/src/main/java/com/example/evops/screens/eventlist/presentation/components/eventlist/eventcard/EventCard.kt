package com.example.evops.screens.eventlist.presentation.components.eventlist.eventcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.evops.screens.eventlist.domain.model.EventItem
import com.example.evops.screens.PreviewData.eventItem
import com.example.evops.screens.eventlist.presentation.components.eventlist.eventcard.components.EventAttendeesButton
import com.example.evops.screens.eventlist.presentation.components.eventlist.eventcard.components.EventImage
import com.example.evops.screens.eventlist.presentation.components.eventlist.eventcard.components.EventPlaceAndDate
import com.example.evops.screens.eventlist.presentation.components.eventlist.eventcard.components.EventPublisherInfo
import com.example.evops.screens.eventlist.presentation.components.eventlist.eventcard.components.EventTitle

@Composable
fun EventCard(
    eventData: EventItem,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = modifier.fillMaxWidth()
    ) {
        EventPublisherInfo(
            eventPublisherData = eventData.eventPublisherData,
            modifier = Modifier
                .padding(horizontal = 18.dp, vertical = 12.dp)
                .fillMaxWidth()
        )

        EventImage(imageUrl = eventData.imageUrl, modifier = Modifier.fillMaxWidth())

        EventTitle(
            title = eventData.title,
            modifier = Modifier
                .padding(horizontal = 18.dp, vertical = 12.dp)
                .fillMaxWidth()
        )

        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 18.dp)
        ) {
            EventAttendeesButton(attendeesCount = eventData.attendeesCount)

            EventPlaceAndDate(
                place = eventData.place,
                date = eventData.date,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview
@Composable
private fun EventCardPreview() {
    EventCard(eventData = eventItem)
}