package com.example.evops.screens.eventlist.data.mappers

import com.example.evops.core.data.model.author.AuthorDto
import com.example.evops.core.data.model.event.EventDto
import com.example.evops.core.data.model.tag.TagDto
import com.example.evops.screens.eventlist.data.dto.EventListDto
import com.example.evops.screens.eventlist.domain.model.EventItem
import com.example.evops.screens.eventlist.domain.model.EventItemAuthor
import com.example.evops.screens.eventlist.domain.model.EventItemTag

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
            tags = this.tags.map { it.toDomain() },
        )

    fun AuthorDto.toDomain() = EventItemAuthor(id = this.id, name = this.name)

    fun TagDto.toDomain() = EventItemTag(id = this.id, name = this.name)
}
