package com.example.evops.screens.evendetails.data.mappers

import com.example.evops.core.data.model.AuthorDto
import com.example.evops.core.data.model.EventDto
import com.example.evops.core.data.model.TagDto
import com.example.evops.screens.evendetails.domain.model.EventDetailsData
import com.example.evops.screens.evendetails.domain.model.EventDetailsPublisherData
import com.example.evops.screens.evendetails.domain.model.TagData
import java.time.LocalDate

object EventDetailsMapper {
    fun EventDto.toDomain() = EventDetailsData(
        eventDetailsPublisherData = this.author.toDomain(),
        eventImageUrls = this.imageUrls,
        title = this.title,
        description = this.description,
        attendeesCount = 0u, // TODO("create default attendees count")
        place = "UI", // TODO("create default place")
        date = LocalDate.now(), // TODO("create default date")
        tagsData = this.tags.map { it.toDomain() }
    )

    fun AuthorDto.toDomain() = EventDetailsPublisherData(
        id = this.id,
        name = this.name,
        avatarPreviewUrl = this.profilePictureUrl ?: "" // TODO("add default url")
    )

    fun TagDto.toDomain() = TagData(
        name = this.name
    )
}