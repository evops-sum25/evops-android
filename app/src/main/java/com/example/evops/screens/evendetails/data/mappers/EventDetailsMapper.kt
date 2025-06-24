package com.example.evops.screens.evendetails.data.mappers

import com.example.evops.core.data.model.AuthorDto
import com.example.evops.core.data.model.EventDto
import com.example.evops.core.data.model.TagDto
import com.example.evops.screens.evendetails.data.EventDetailsDto
import com.example.evops.screens.evendetails.domain.model.EventDetails
import com.example.evops.screens.evendetails.domain.model.EventDetailsPublisher
import com.example.evops.screens.evendetails.domain.model.EventDetailsTag
import java.time.LocalDate

object EventDetailsMapper {
    fun EventDetailsDto.toDomain() = EventDetails(
        eventDetailsPublisher = this.event.author.toDomain(),
        eventImageUrls = this.event.imageUrls,
        title = this.event.title,
        description = this.event.description,
        attendeesCount = 0u, // TODO("create default attendees count")
        place = "UI", // TODO("create default place")
        date = LocalDate.now(), // TODO("create default date")
        tagsData = this.event.tags.map { it.toDomain() }
    )

    fun AuthorDto.toDomain() = EventDetailsPublisher(
        id = this.id,
        name = this.name,
        avatarPreviewUrl = this.profilePictureUrl ?: "" // TODO("add default url")
    )

    fun TagDto.toDomain() = EventDetailsTag(
        name = this.name
    )
}