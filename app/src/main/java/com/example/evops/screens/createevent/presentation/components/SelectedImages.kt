package com.example.evops.screens.createevent.presentation.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.evops.screens.createevent.presentation.CreateEventEvent

@Composable
fun SelectedImages(
    imageUris: List<Uri>,
    deletingUris: List<Uri>,
    onEvent: (CreateEventEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Text(
        "Attached Images",
        style = MaterialTheme.typography.titleSmall,
        modifier = Modifier.padding(vertical = 4.dp),
    )
    LazyRow(horizontalArrangement = Arrangement.spacedBy(4.dp), modifier = modifier) {
        items(count = imageUris.count()) {
            val imageUri = imageUris[it]
            SelectedImage(
                imageUri = imageUri,
                isDeletingImage = deletingUris.contains(imageUri),
                onEvent = onEvent,
                modifier = Modifier.size(160.dp),
            )
        }
    }
}

@Composable
fun SelectedImage(
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
