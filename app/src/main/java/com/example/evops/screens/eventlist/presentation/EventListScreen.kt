package com.example.evops.screens.eventlist.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.evops.R
import com.example.evops.screens.eventlist.presentation.components.eventlist.EventList
import com.example.evops.screens.eventlist.presentation.components.searchfield.SearchField

@Composable
fun EventListScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: EventListViewModel = hiltViewModel(),
) {
    val listState by viewModel.listState.collectAsStateWithLifecycle()

    Scaffold { innerPadding ->
        Column(modifier = modifier.padding(innerPadding).fillMaxSize().imePadding()) {
            SearchField(
                searchString = listState.searchString,
                onEvent = viewModel::onEvent,
                modifier = Modifier.padding(horizontal = 18.dp, vertical = 8.dp),
            )
            listState.events?.let { events ->
                if (events.isEmpty()) {
                    Text(stringResource(R.string.no_events_found))
                } else {
                    EventList(
                        events = events,
                        searchTagIds = listState.searchTagIds,
                        onEvent = viewModel::onEvent,
                        navController = navController,
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }
        }
    }
}
