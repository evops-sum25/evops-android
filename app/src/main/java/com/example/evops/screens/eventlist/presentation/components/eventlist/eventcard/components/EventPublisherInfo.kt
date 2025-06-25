package com.example.evops.screens.eventlist.presentation.components.eventlist.eventcard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.evops.R
import com.example.evops.screens.eventlist.domain.model.EventItemPublisher
import com.example.evops.screens.PreviewData.eventItemPublisher

@Composable
fun EventPublisherInfo(
    eventPublisherData: EventItemPublisher,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = modifier
    ) {
        AvatarPreview(avatarPreviewUrl = eventPublisherData.avatarPreviewUrl)

        PublisherTitle(
            publisherName = eventPublisherData.name,
            modifier = Modifier.padding(horizontal = 6.dp)
        )
    }
}

@Composable
private fun AvatarPreview(
    avatarPreviewUrl: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(avatarPreviewUrl)
            .build(),
        contentDescription = stringResource(R.string.description_publisher_avatar_preview),
        placeholder = painterResource(R.drawable.publisher_avatar_placeholder),
        error = painterResource(R.drawable.publisher_avatar_placeholder),
        modifier = modifier
            .clip(CircleShape)
            .size(42.dp),
    )
}

@Composable
private fun PublisherTitle(
    publisherName: String,
    modifier: Modifier = Modifier
) {
    Text(text = publisherName, modifier = modifier)
}

@Preview
@Composable
private fun EventPublisherInfoPreview() {
    EventPublisherInfo(
        eventPublisherData = eventItemPublisher
    )
}