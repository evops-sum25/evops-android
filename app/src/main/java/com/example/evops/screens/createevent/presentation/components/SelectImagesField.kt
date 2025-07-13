package com.example.evops.screens.createevent.presentation.components

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
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
    deletingUris: List<Uri>,
    canAddMoreImages: Boolean,
    onEvent: (CreateEventEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        SelectedImages(
            imageUris = selectedUris,
            deletingUris = deletingUris,
            onEvent = onEvent,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        )

        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            TextButton(
                onClick = { onEvent(CreateEventEvent.DeleteImages(deletingUris)) },
                enabled = deletingUris.isNotEmpty(),
            ) {
                Text(stringResource(R.string.remove_images))
            }
            Spacer(modifier = Modifier.size(8.dp))
            Button(
                onClick = { onEvent(CreateEventEvent.OpenHideImagePicker(true)) },
                enabled = canAddMoreImages,
            ) {
                Text(stringResource(R.string.add_images))
            }
        }
    }
}
