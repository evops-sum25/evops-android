package com.example.evops.screens.eventlist.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.evops.screens.eventlist.presentation.components.eventlist.EventList
import com.example.evops.screens.eventlist.presentation.components.searchfield.SearchField

@Composable
fun EventListScreen(
    modifier: Modifier = Modifier, viewModel: EventListViewModel = hiltViewModel()
) {
    val listState by viewModel.listState.collectAsStateWithLifecycle()

    Column(modifier = modifier.fillMaxSize()) {
        SearchField(
            modifier = Modifier.padding(
                horizontal = 18.dp,
                vertical = 4.dp
            )
        )

        EventList(events = listState.events, modifier = Modifier.fillMaxSize())
    }
}

@Preview
@Composable
private fun EventListScreenPreview() {

}