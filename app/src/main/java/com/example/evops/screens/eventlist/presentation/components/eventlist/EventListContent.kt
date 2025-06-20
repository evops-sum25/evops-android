package com.example.evops.screens.eventlist.presentation.components.eventlist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.evops.screens.PreviewData
import com.example.evops.screens.eventlist.domain.model.EventItem
import com.example.evops.screens.eventlist.presentation.components.eventlist.eventcard.EventCard

@Composable
fun EventListContent(
    events: List<EventItem>,
    modifier: Modifier = Modifier
) {
    LazyColumn(state = rememberLazyListState(), modifier = modifier) {
        items(
            items = events,
            key = { event -> event.id }
        ) { event ->
            EventCard(
                eventData = event,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
private fun EventListPreview() {
    EventListContent(
        events = listOf(
            PreviewData.eventItem,
            PreviewData.eventItem,
            PreviewData.eventItem,
        ),
        modifier = Modifier.fillMaxSize()
    )
}