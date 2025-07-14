package com.example.evops.core.presentation.components.image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.evops.R
import com.example.evops.core.common.Config
import com.example.evops.screens.PreviewData

@Composable
fun EventImage(imageId: String, modifier: Modifier = Modifier) {
    val imageUrl = Config.constructImageUrl(imageId)
    val context = LocalContext.current
    val imageRequest = remember(imageUrl) { ImageRequest.Builder(context).data(imageUrl).build() }

    Box(modifier = modifier.aspectRatio(1f)) {
        // Background layer - blurred with proper scaling
        SubcomposeAsyncImage(
            model = imageRequest,
            loading = { LoadingImagePlaceholder() },
            error = { LoadingImagePlaceholder() },
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier.matchParentSize().blur(12.dp),
        )

        // Foreground layer - properly fitted image
        SubcomposeAsyncImage(
            model = imageRequest,
            loading = { LoadingImagePlaceholder() },
            error = { LoadingImagePlaceholder() },
            contentScale = ContentScale.Fit,
            contentDescription = stringResource(R.string.description_event_image_preview),
            modifier = Modifier.matchParentSize(),
        )
    }
}

@Composable
private fun LoadingImagePlaceholder(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier =
            modifier.background(MaterialTheme.colorScheme.surface).padding(96.dp).fillMaxSize(),
    ) {
        CircularProgressIndicator(strokeWidth = 6.dp, modifier = Modifier.fillMaxSize())
    }
}

@Preview
@Composable
private fun EventImagePreview() {
    EventImage(imageId = PreviewData.eventItem.imageUrl)
}
