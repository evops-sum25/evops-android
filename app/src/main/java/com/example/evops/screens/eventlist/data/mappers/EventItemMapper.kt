package com.example.evops.screens.eventlist.data.mappers

import com.example.evops.core.data.model.author.AuthorDto
import com.example.evops.core.data.model.event.EventDto
import com.example.evops.screens.eventlist.data.dto.EventListDto
import com.example.evops.screens.eventlist.domain.model.EventItem
import com.example.evops.screens.eventlist.domain.model.EventItemAuthor
import java.time.LocalDate

object EventItemMapper {
    fun EventListDto.toDomain(): List<EventItem> {
        return this.events.map { it.toDomain() }
    }

    fun EventDto.toDomain() =
        EventItem(
            id = this.id,
            title = this.title,
            author = this.author.toDomain(),
            imageUrl = this.imageIds.firstOrNull(),
            description = this.description,
            attendeesCount = 0u, // TODO("create default attendees count")
            place = "UI", // TODO("create default place")
            date = LocalDate.now(), // TODO("create default date")
        )

    fun AuthorDto.toDomain() = EventItemAuthor(id = this.id, name = this.name)
}
