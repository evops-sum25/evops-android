package com.example.evops.screens.createevent.presentation.components.images

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.evops.R
import com.example.evops.screens.createevent.presentation.CreateEventEvent

@Composable
fun SelectedImages(
    imageUris: List<Uri>,
    deletingUris: List<Uri>,
    onEvent: (CreateEventEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { imageUris.size })

    if (imageUris.isNotEmpty()) {
        SelectedImagesPager(
            pagerState = pagerState,
            imageUris = imageUris,
            deletingUris = deletingUris,
            onEvent = onEvent,
            modifier = modifier,
        )
    } else {
        NoImagesStub(modifier = modifier)
    }
}

@Composable
private fun SelectedImagesPager(
    pagerState: PagerState,
    imageUris: List<Uri>,
    deletingUris: List<Uri>,
    onEvent: (CreateEventEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    HorizontalPager(state = pagerState, modifier = modifier) { page ->
        val imageUri = imageUris[page]
        SelectedImage(
            imageUri = imageUri,
            isDeletingImage = deletingUris.contains(imageUri),
            onEvent = onEvent,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun SelectedImage(
    imageUri: Uri,
    isDeletingImage: Boolean,
    onEvent: (CreateEventEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val event =
        if (isDeletingImage) CreateEventEvent.RemoveDeletingImage(imageUri)
        else CreateEventEvent.AddDeletingImage(imageUri)
    Box(
        modifier =
            modifier
                .aspectRatio(1f)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { onEvent(event) },
                )
    ) {
        SubcomposeAsyncImage(
            model = imageUri,
            loading = { LoadingImagePlaceholder() },
            error = { LoadingImagePlaceholder() },
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
        )

        RadioButton(
            selected = isDeletingImage,
            onClick = { onEvent(event) },
            modifier = Modifier.align(Alignment.TopEnd),
        )
    }
}

@Composable
private fun LoadingImagePlaceholder(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier =
            modifier.background(MaterialTheme.colorScheme.surface).padding(16.dp).fillMaxSize(),
    ) {
        CircularProgressIndicator(strokeWidth = 2.dp, modifier = Modifier.fillMaxSize())
    }
}

@Composable
private fun NoImagesStub(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.aspectRatio(1f).background(MaterialTheme.colorScheme.surface),
    ) {
        Text(
            stringResource(R.string.try_adding_some_images_to_the_event),
            modifier = Modifier.padding(vertical = 8.dp),
        )
    }
}
