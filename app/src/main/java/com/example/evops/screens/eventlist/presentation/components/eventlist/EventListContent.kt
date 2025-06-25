package com.example.evops.screens.eventlist.presentation.components.eventlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.evops.core.navigation.Destination
import com.example.evops.screens.PreviewData
import com.example.evops.screens.eventlist.domain.model.EventItem
import com.example.evops.screens.eventlist.presentation.components.eventlist.eventcard.EventCard

@Composable
fun EventListContent(
    events: List<EventItem>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    LazyColumn(state = rememberLazyListState(), modifier = modifier) {
        items(
            items = events,
            key = { event -> event.id }
        ) { event ->
            EventCard(
                eventData = event,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate(Destination.EventDetails(eventId = event.id)) }
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
        navController = rememberNavController(),
        modifier = Modifier.fillMaxSize()
    )
}