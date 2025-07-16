package com.example.evops.screens.evendetails.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.evops.core.presentation.components.image.EventImage
import com.example.evops.screens.PreviewData

@Composable
fun EventDetailsImagePager(imageIds: List<String>, modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState(pageCount = { imageIds.size })
    HorizontalPager(state = pagerState, modifier = modifier) { page ->
        EventImage(imageId = imageIds[page], modifier = Modifier.fillMaxWidth())
    }
}

@Preview
@Composable
private fun EventDetailsImagePagerPreview() {
    EventDetailsImagePager(
        imageIds = listOf(PreviewData.eventItem.imageUrl!!, PreviewData.eventItem.imageUrl)
    )
}
