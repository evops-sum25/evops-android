package com.example.evops.screens.eventlist.presentation.components.eventcard.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.evops.screens.eventlist.presentation.components.PreviewData

@Composable
fun EventTitle(title: String, modifier: Modifier = Modifier) {
    Text(text = title, modifier = modifier)
}

@Preview
@Composable
private fun EventTitlePreview() {
    EventTitle(title = PreviewData.eventData.title)
}