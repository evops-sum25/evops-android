package com.example.evops.screens.eventlist.presentation.components.eventlist.eventcard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.evops.screens.PreviewData.eventItemPublisher
import com.example.evops.screens.eventlist.domain.model.EventItemPublisher

@Composable
fun EventPublisherInfo(
    eventPublisherData: EventItemPublisher,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = modifier,
    ) {
        PublisherTitle(
            publisherName = eventPublisherData.name,
            modifier = Modifier,
        )
    }
}

@Composable
private fun PublisherTitle(
    publisherName: String,
    modifier: Modifier = Modifier,
) {
    Text(text = publisherName, modifier = modifier)
}

@Preview
@Composable
private fun EventPublisherInfoPreview() {
    EventPublisherInfo(
        eventPublisherData = eventItemPublisher,
    )
}
