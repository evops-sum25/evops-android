package com.example.evops.screens.createevent.data.mappers

import com.example.evops.screens.createevent.data.model.CreateEventFormDto
import com.example.evops.screens.createevent.data.model.CreateEventFormWrapperDto
import com.example.evops.screens.createevent.data.model.CreateUserFormDto
import com.example.evops.screens.createevent.data.model.CreateUserFormWrapperDto
import com.example.evops.screens.createevent.domain.model.CreateEventForm
import com.example.evops.screens.createevent.domain.model.CreateUserForm

object CreateEventMapper {
    fun CreateEventForm.toData(authorId: String) =
        CreateEventFormWrapperDto(
            form =
                CreateEventFormDto(
                    authorId = authorId,
                    description = this.description,
                    imageUrls = this.imageUrls,
                    tagIds = this.tagIds,
                    title = this.title,
                    withAttendance = this.withAttendance,
                ),
        )

    fun CreateUserForm.toData() =
        CreateUserFormWrapperDto(
            form =
                CreateUserFormDto(
                    name = this.name,
                    profilePictureUrl = null,
                ),
        )
}
