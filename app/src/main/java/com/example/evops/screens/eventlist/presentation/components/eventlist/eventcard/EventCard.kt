package com.example.evops.screens.eventlist.presentation.components.eventlist.eventcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.evops.core.presentation.components.image.EventImage
import com.example.evops.screens.eventlist.domain.model.EventItem
import com.example.evops.screens.eventlist.presentation.EventListEvent
import com.example.evops.screens.eventlist.presentation.components.eventlist.eventcard.components.EventDescription
import com.example.evops.screens.eventlist.presentation.components.eventlist.eventcard.components.EventTags
import com.example.evops.screens.eventlist.presentation.components.eventlist.eventcard.components.EventTitleAndAuthor

@Composable
fun EventCard(
    eventData: EventItem,
    searchTagIds: List<String>,
    onEvent: (EventListEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = modifier,
    ) {
        EventTitleAndAuthor(
            title = eventData.title,
            author = eventData.author,
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 12.dp),
        )
        eventData.imageUrl?.let {
            EventImage(
                imageId = eventData.imageUrl,
                modifier = Modifier.padding(bottom = 12.dp).fillMaxWidth(),
            )
        }
        EventDescription(
            description = eventData.description,
            isExpanded = eventData.imageUrl == null,
            modifier = Modifier.padding(start = 18.dp, end = 18.dp, bottom = 12.dp).fillMaxWidth(),
        )
        EventTags(
            tagsData = eventData.tags,
            searchTagIds = searchTagIds,
            onEvent = onEvent,
            modifier = Modifier.padding(start = 18.dp, end = 18.dp, bottom = 18.dp),
        )
        HorizontalDivider(thickness = 2.dp)
    }
}
