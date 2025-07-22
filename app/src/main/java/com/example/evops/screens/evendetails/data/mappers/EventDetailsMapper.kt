package com.example.evops.screens.evendetails.data.mappers

import com.example.evops.core.data.model.author.AuthorDto
import com.example.evops.core.data.model.event.EventWrapperDto
import com.example.evops.core.data.model.tag.TagDto
import com.example.evops.screens.evendetails.domain.model.EventDetails
import com.example.evops.screens.evendetails.domain.model.EventDetailsAuthor
import com.example.evops.screens.evendetails.domain.model.EventDetailsTag

object EventDetailsMapper {
    fun EventWrapperDto.toDomain() =
        EventDetails(
            author = this.event.author.toDomain(),
            imageUrls = this.event.imageIds,
            title = this.event.title,
            description = this.event.description,
            tagsData = this.event.tags.map { it.toDomain() },
        )

    fun AuthorDto.toDomain() = EventDetailsAuthor(id = this.id, name = this.name)

    fun TagDto.toDomain() = EventDetailsTag(name = this.name)
}
