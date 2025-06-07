package com.example.evops.screens.eventlist.presentation.components.eventcard.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.transformations
import coil3.transform.RoundedCornersTransformation
import com.example.evops.R
import com.example.evops.screens.eventlist.domain.model.PublisherData
import com.example.evops.screens.eventlist.presentation.components.PreviewData.publisherData

@Composable
fun EventPublisherInfo(publisherData: PublisherData, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        AvatarPreview(avatarPreviewUrl = publisherData.avatarPreviewUrl)
        PublisherTitle(publisherName = publisherData.name)
    }
}

@Composable
private fun AvatarPreview(avatarPreviewUrl: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(avatarPreviewUrl)
            .transformations(RoundedCornersTransformation(16f))
            .build(),
        contentDescription = stringResource(R.string.description_publisher_avatar_preview),
        placeholder = painterResource(R.drawable.publisher_avatar_placeholder),
        error = painterResource(R.drawable.publisher_avatar_placeholder),
        modifier = modifier,
    )
//    TODO("add error placeholder")
}

@Composable
private fun PublisherTitle(publisherName: String, modifier: Modifier = Modifier) {
    Text(text = publisherName, modifier = modifier)
}

@Preview
@Composable
private fun EventPublisherInfoPreview() {
    EventPublisherInfo(
        publisherData = publisherData
    )
}