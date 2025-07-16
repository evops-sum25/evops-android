package com.example.evops.screens.eventlist.presentation.components.eventlist.eventcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.evops.core.presentation.components.image.EventImage
import com.example.evops.screens.PreviewData.eventItem
import com.example.evops.screens.eventlist.domain.model.EventItem
import com.example.evops.screens.eventlist.presentation.components.eventlist.eventcard.components.EventDescription
import com.example.evops.screens.eventlist.presentation.components.eventlist.eventcard.components.EventTitleAndAuthor

@Composable
fun EventCard(eventData: EventItem, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = modifier,
    ) {
        eventData.imageUrl?.let {
            EventImage(imageId = eventData.imageUrl, modifier = Modifier.padding(top = 12.dp).fillMaxWidth())
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier =
                Modifier.padding(start = 18.dp, end = 18.dp, top = 12.dp, bottom = 18.dp)
                    .fillMaxWidth(),
        ) {
            EventTitleAndAuthor(title = eventData.title, author = eventData.author)
            EventDescription(
                description = eventData.description,
                isExpanded = eventData.imageUrl == null,
            )
        }
        HorizontalDivider()
    }
}

@Preview
@Composable
private fun EventCardPreview() {
    EventCard(eventData = eventItem)
}
