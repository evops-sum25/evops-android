package com.example.evops.screens.createevent.presentation.components.images

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.evops.R
import com.example.evops.screens.createevent.presentation.CreateEventEvent

@Composable
fun SelectImagesField(
    selectedUris: List<Uri>,
    canAddMoreImages: Boolean,
    onEvent: (CreateEventEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { selectedUris.size })
    Column(modifier = modifier) {
        SelectedImages(
            imageUris = selectedUris,
            pagerState = pagerState,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        )

        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            TextButton(
                onClick = { onEvent(CreateEventEvent.DeleteImage(pagerState.currentPage)) },
                enabled = selectedUris.isNotEmpty(),
            ) {
                Text(stringResource(R.string.remove_images))
            }
            Spacer(modifier = Modifier.size(8.dp))
            IconButton(
                onClick = { onEvent(CreateEventEvent.OpenHideImagePicker(true)) },
                enabled = canAddMoreImages,
            ) {
                Icon(imageVector = Icons.Filled.AttachFile, contentDescription = null)
            }
        }
    }
}
