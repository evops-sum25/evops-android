package com.example.evops.screens.eventlist.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
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
        Box(modifier = modifier.padding(innerPadding).fillMaxSize()) {
            EventList(
                events = listState.events,
                onEvent = viewModel::onEvent,
                navController = navController,
                modifier = Modifier.fillMaxSize(),
            )
            SearchField(
                searchString = listState.searchString,
                onEvent = viewModel::onEvent,
                modifier = Modifier.padding(horizontal = 18.dp, vertical = 4.dp),
            )
        }
    }
}

@Preview @Composable private fun EventListScreenPreview() {}
