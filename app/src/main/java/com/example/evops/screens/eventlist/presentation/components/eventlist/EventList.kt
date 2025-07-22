package com.example.evops.screens.eventlist.presentation.components.eventlist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.evops.screens.eventlist.domain.model.EventItem
import com.example.evops.screens.eventlist.presentation.EventListEvent

@Composable
fun EventList(
    events: List<EventItem>,
    searchTagIds: List<String>,
    onEvent: (EventListEvent) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    SwipeRefreshWrapper(
        onRefresh = {
            onEvent(EventListEvent.LoadFirstEvents)
            onEvent(EventListEvent.UpdateSearchString(""))
        },
        modifier = modifier.fillMaxSize(),
    ) {
        EventListContent(
            events = events,
            searchTagIds = searchTagIds,
            onEvent = onEvent,
            navController = navController,
            modifier = Modifier.fillMaxSize(),
        )
    }
}
