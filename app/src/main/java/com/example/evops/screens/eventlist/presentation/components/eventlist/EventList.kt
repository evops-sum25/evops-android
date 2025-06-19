package com.example.evops.screens.eventlist.presentation.components.eventlist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.evops.screens.eventlist.domain.model.EventItem

@Composable
fun EventList(
    events: List<EventItem>,
    modifier: Modifier = Modifier
) {
    EventListContent(events = events, modifier = Modifier.fillMaxSize())
}
