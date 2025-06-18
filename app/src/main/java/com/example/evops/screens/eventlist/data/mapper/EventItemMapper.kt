package com.example.evops.screens.eventlist.data.mapper

import com.example.evops.core.data.model.AuthorDto
import com.example.evops.core.data.model.EventDto
import com.example.evops.screens.eventlist.domain.model.EventItemData
import com.example.evops.screens.eventlist.domain.model.EventItemPublisherData
import java.time.LocalDate

object EventItemMapper {
    fun EventDto.toDomain() = EventItemData(
        title = this.title,
        eventPublisherData = this.author.toDomain(),
        imageUrl = this.imageUrls.first(), // TODO("handle empty list")
        attendeesCount = 0u, // TODO("create default attendees count")
        place = "UI", // TODO("create default place")
        date = LocalDate.now(), // TODO("create default date")

    )

    fun AuthorDto.toDomain() = EventItemPublisherData(
        id = this.id,
        name = this.name,
        avatarPreviewUrl = this.profilePictureUrl ?: "" // TODO("add default url")
    )
}