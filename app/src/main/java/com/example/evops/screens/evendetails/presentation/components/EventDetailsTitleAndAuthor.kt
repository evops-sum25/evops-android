package com.example.evops.screens.evendetails.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.evops.screens.evendetails.domain.model.EventDetailsAuthor

@Composable
fun EventDetailsTitleAndAuthor(
    title: String,
    author: EventDetailsAuthor,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        EventDetailsTitle(title = title, modifier = Modifier.padding(bottom = 2.dp))

        EventDetailsAuthorInfo(author = author)
    }
}

@Composable
private fun EventDetailsTitle(title: String, modifier: Modifier = Modifier) {
    Text(text = title, style = MaterialTheme.typography.titleMedium, modifier = modifier)
}

@Composable
private fun EventDetailsAuthorInfo(author: EventDetailsAuthor, modifier: Modifier = Modifier) {
    Text(
        text = author.name,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
        modifier = modifier,
    )
}
