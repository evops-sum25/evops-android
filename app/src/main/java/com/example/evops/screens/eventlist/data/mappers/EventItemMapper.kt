package com.example.evops.screens.eventlist.data.mappers

import android.util.Log
import com.example.evops.core.data.model.UserDto
import com.example.evops.core.data.model.EventDto
import com.example.evops.screens.eventlist.data.dto.EventListDto
import com.example.evops.screens.eventlist.domain.model.EventItem
import com.example.evops.screens.eventlist.domain.model.EventItemPublisher
import java.time.LocalDate

object EventItemMapper {
    fun EventListDto.toDomain(): List<EventItem> {
        return this.events.map { it.toDomain() }
    }
    
    fun EventDto.toDomain() = EventItem(
        id = this.id,
        title = this.title,
        eventPublisherData = this.author.toDomain(),
        imageUrl = this.imageUrls.firstOrNull() ?: "", // TODO("add default url")
        attendeesCount = 0u, // TODO("create default attendees count")
        place = "UI", // TODO("create default place")
        date = LocalDate.now(), // TODO("create default date")

    )

    fun UserDto.toDomain() = EventItemPublisher(
        id = this.id,
        name = this.name,
        avatarPreviewUrl = this.profilePictureUrl ?: "" // TODO("add default url")
    )
}