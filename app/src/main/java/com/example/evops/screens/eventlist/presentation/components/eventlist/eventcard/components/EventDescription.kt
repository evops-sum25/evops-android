package com.example.evops.screens.eventlist.presentation.components.eventlist.eventcard.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun EventDescription(description: String, isExpanded: Boolean, modifier: Modifier = Modifier) {
    val maxLines = if (isExpanded) 7 else 3
    Text(
        text = description,
        maxLines = maxLines,
        modifier = modifier,
        overflow = TextOverflow.Ellipsis,
    )
}
