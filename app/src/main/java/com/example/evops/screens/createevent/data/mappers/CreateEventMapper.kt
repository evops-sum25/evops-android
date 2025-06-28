package com.example.evops.screens.createevent.data.mappers

import com.example.evops.screens.createevent.data.model.CreateAuthorFormDto
import com.example.evops.screens.createevent.data.model.CreateAuthorFormWrapperDto
import com.example.evops.screens.createevent.data.model.CreateEventFormDto
import com.example.evops.screens.createevent.data.model.CreateEventFormWrapperDto
import com.example.evops.screens.createevent.domain.model.CreateAuthorForm
import com.example.evops.screens.createevent.domain.model.CreateEventForm

object CreateEventMapper {
    fun CreateEventForm.toData(authorId: String) =
        CreateEventFormWrapperDto(
            eventForm =
                CreateEventFormDto(
                    authorId = authorId,
                    description = this.description,
                    imageUrls = this.imageUrls,
                    tagIds = this.tagIds,
                    title = this.title,
                    withAttendance = this.withAttendance,
                ),
        )

    fun CreateAuthorForm.toData() =
        CreateAuthorFormWrapperDto(
            authorForm =
                CreateAuthorFormDto(
                    name = this.name,
                ),
        )
}
