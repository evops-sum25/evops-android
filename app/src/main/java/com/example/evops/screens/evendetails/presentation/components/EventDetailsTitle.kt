package com.example.evops.screens.evendetails.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.evops.screens.PreviewData

@Composable
fun EventDetailsTitle(title: String, modifier: Modifier = Modifier) {
    Text(text = title, modifier = modifier)
}

@Preview
@Composable
private fun EventDetailsTitlePreview() {
    EventDetailsTitle(title = PreviewData.eventItemData.title)
}