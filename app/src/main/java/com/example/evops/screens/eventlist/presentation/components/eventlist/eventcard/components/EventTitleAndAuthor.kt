package com.example.evops.screens.eventlist.presentation.components.eventlist.eventcard.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.evops.screens.eventlist.domain.model.EventItemAuthor

@Composable
fun EventTitleAndAuthor(title: String, author: EventItemAuthor, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        EventTitle(title = title, modifier = Modifier.padding(bottom = 2.dp))

        EventAuthorInfo(author = author, modifier = Modifier)
    }
}

@Composable
private fun EventTitle(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier,
    )
}

@Composable
private fun EventAuthorInfo(author: EventItemAuthor, modifier: Modifier = Modifier) {
    Text(
        text = author.name,
        style = MaterialTheme.typography.bodyMedium,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
        modifier = modifier,
    )
}
