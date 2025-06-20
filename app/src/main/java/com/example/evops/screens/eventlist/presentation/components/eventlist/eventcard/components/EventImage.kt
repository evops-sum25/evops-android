package com.example.evops.screens.eventlist.presentation.components.eventlist.eventcard.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.evops.R
import com.example.evops.screens.PreviewData

@Composable
fun EventImage(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .build(),
        contentDescription = stringResource(R.string.description_event_image_preview),
        placeholder = painterResource(R.drawable.image_placeholder),
        error = painterResource(R.drawable.image_placeholder),
        contentScale = ContentScale.FillWidth,
        modifier = modifier,
    )
//    TODO("add error placeholder")
}

@Preview
@Composable
private fun EventImagePreview() {
    EventImage(imageUrl = PreviewData.eventItem.imageUrl)
}