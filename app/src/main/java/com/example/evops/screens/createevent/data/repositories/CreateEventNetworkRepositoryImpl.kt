package com.example.evops.screens.createevent.data.repositories

import com.example.evops.screens.createevent.data.api.CreateEventApi
import com.example.evops.screens.createevent.data.mappers.CreateEventMapper.toData
import com.example.evops.screens.createevent.domain.model.CreateAuthorForm
import com.example.evops.screens.createevent.domain.model.CreateEventForm
import com.example.evops.screens.createevent.domain.repositories.CreateEventNetworkRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class CreateEventNetworkRepositoryImpl(private val createEventApi: CreateEventApi) :
    CreateEventNetworkRepository {
    override suspend fun getAuthorIds(): List<String> {
        return createEventApi.getUsers().users.map { it.id }
    }

    override suspend fun createAuthor(userForm: CreateAuthorForm) {
        createEventApi.createUser(userForm.toData())
    }

    override suspend fun createEvent(eventForm: CreateEventForm, userId: String): String {
        return createEventApi.createEvent(eventForm.toData(userId)).eventId
    }

    override suspend fun postImage(eventId: String, image: File) {
        val imageMultipart =
            MultipartBody.Part.createFormData(
                name = "image",
                filename = image.name,
                body = image.asRequestBody(),
            )
        createEventApi.postImage(eventId = eventId, image = imageMultipart)
    }
}
