package com.example.evops.screens.eventlist.presentation.components.eventlist.eventcard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.evops.screens.PreviewData
import java.time.LocalDate

@Composable
fun EventPlaceAndDate(
    place: String,
    date: LocalDate,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Top,
        modifier = modifier,
    ) {
        EventPlace(place = place)

        EventDate(date = date)
    }
}

@Composable
private fun EventPlace(
    place: String,
    modifier: Modifier = Modifier,
) {
    Text(text = place, modifier = modifier)
}

@Composable
private fun EventDate(
    date: LocalDate,
    modifier: Modifier = Modifier,
) {
    Text(text = date.toString(), modifier = modifier)
}

@Preview
@Composable
private fun EventPlaceAndDatePreview() {
    EventPlaceAndDate(place = PreviewData.eventItem.place, date = PreviewData.eventItem.date)
}
