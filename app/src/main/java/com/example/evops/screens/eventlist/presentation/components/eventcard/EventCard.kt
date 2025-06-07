package com.example.evops.screens.eventlist.presentation.components.eventcard

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.evops.screens.eventlist.domain.model.EventData
import com.example.evops.screens.eventlist.presentation.components.PreviewData.eventData
import com.example.evops.screens.eventlist.presentation.components.eventcard.components.EventPublisherInfo

@Composable
fun EventCard(
    eventData: EventData, modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        EventPublisherInfo(
            publisherData = eventData.publisherData, modifier = modifier
        )
    }
}

@Preview
@Composable
private fun EventCardPreview() {
    EventCard(eventData = eventData)
}