package com.example.evops.screens.evendetails.presentation.components

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
fun EventDetailsPlaceAndDate(
    place: String,
    date: LocalDate,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Top,
        modifier = modifier,
    ) {
        EventDetailsPlace(place = place)

        EventDetailsDate(date = date)
    }
}

@Composable
private fun EventDetailsPlace(
    place: String,
    modifier: Modifier = Modifier,
) {
    Text(text = place, modifier = modifier)
}

@Composable
private fun EventDetailsDate(
    date: LocalDate,
    modifier: Modifier = Modifier,
) {
    Text(text = date.toString(), modifier = modifier)
}

@Preview
@Composable
private fun EventDetailsPlaceAndDatePreview() {
    EventDetailsPlaceAndDate(
        place = PreviewData.eventDetails.place,
        date = PreviewData.eventDetails.date,
    )
}
