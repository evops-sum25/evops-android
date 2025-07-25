package com.example.evops.screens.eventlist.presentation.components.eventlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.evops.core.common.Config
import com.example.evops.core.navigation.Destination
import com.example.evops.screens.eventlist.domain.model.EventItem
import com.example.evops.screens.eventlist.presentation.EventListEvent
import com.example.evops.screens.eventlist.presentation.components.eventlist.eventcard.EventCard

@Composable
fun EventListContent(
    events: List<EventItem>,
    searchTagIds: List<String>,
    onEvent: (EventListEvent) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val lazyListState = rememberLazyListState()
    val shouldLoadMore by remember {
        derivedStateOf {
            val layoutInfo = lazyListState.layoutInfo
            val totalItems = layoutInfo.totalItemsCount
            val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

            lastVisibleItem >= totalItems - Config.EVENT_PAGE_LIMIT / 2
        }
    }
    LaunchedEffect(shouldLoadMore) {
        if (shouldLoadMore) {
            onEvent(EventListEvent.LoadEvents)
        }
    }

    LazyColumn(state = lazyListState, modifier = modifier) {
        item { HorizontalDivider(thickness = 2.dp) }
        items(items = events, key = { event -> event.id }) { event ->
            EventCard(
                eventData = event,
                searchTagIds = searchTagIds,
                onEvent = onEvent,
                modifier =
                    Modifier.fillMaxWidth().clickable {
                        navController.navigate(Destination.EventDetails(eventId = event.id))
                    },
            )
        }
    }
}
