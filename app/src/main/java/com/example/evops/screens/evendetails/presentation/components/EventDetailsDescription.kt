package com.example.evops.screens.evendetails.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.evops.screens.PreviewData

@Composable
fun EventDetailsDescription(description: String, modifier: Modifier = Modifier) {
    Text(text = description, style = MaterialTheme.typography.bodyMedium, modifier = modifier)
}

@Preview
@Composable
private fun EventDetailsDescriptionPreview() {
    EventDetailsDescription(description = PreviewData.eventDetails.description)
}
