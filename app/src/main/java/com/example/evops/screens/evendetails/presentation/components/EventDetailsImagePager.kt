package com.example.evops.screens.evendetails.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.evops.R
import com.example.evops.screens.PreviewData

@Composable
fun EventDetailsImagePager(
    imageUrls: List<String>,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(pageCount = { imageUrls.size })
    HorizontalPager(state = pagerState, modifier = modifier) { page ->
        EventDetailsImage(imageUrl = imageUrls[page], modifier = Modifier.fillMaxWidth())
    }
}

@Composable
private fun EventDetailsImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model =
            ImageRequest.Builder(LocalContext.current)
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
private fun EventDetailsImagePagerPreview() {
    EventDetailsImagePager(
        imageUrls =
            listOf(
                PreviewData.eventItem.imageUrl,
                PreviewData.eventItem.imageUrl,
            ),
    )
}
