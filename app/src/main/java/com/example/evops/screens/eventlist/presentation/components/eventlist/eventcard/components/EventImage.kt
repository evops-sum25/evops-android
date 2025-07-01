package com.example.evops.screens.eventlist.presentation.components.eventlist.eventcard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.evops.R
import com.example.evops.screens.PreviewData

@Composable
fun EventImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val imageRequest = remember(imageUrl) {
        ImageRequest.Builder(context)
            .data(imageUrl)
            .build()
    }

    Box(modifier = modifier.aspectRatio(1f)) {
        // Background layer - blurred with proper scaling
        SubcomposeAsyncImage(
            model = imageRequest,
            loading = { ImagePlaceholder() },
            error = { ImagePlaceholder() },
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .matchParentSize()
                .blur(12.dp)
        )

        // Foreground layer - properly fitted image
        SubcomposeAsyncImage(
            model = imageRequest,
            loading = { ImagePlaceholder() },
            error = { ImagePlaceholder() },
            contentScale = ContentScale.Fit,
            contentDescription = stringResource(R.string.description_event_image_preview),
            modifier = Modifier.matchParentSize()
        )
    }
}

@Composable
private fun ImagePlaceholder() {
    Image(
        painter = painterResource(R.drawable.image_placeholder),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillWidth
    )
}

@Preview
@Composable
private fun EventImagePreview() {
    EventImage(imageUrl = PreviewData.eventItem.imageUrl)
}
