package com.example.evops.screens.createevent.data.repositories

import com.example.evops.screens.createevent.data.api.CreateEventApi
import com.example.evops.screens.createevent.data.mappers.CreateEventMapper.toData
import com.example.evops.screens.createevent.domain.model.CreateAuthorForm
import com.example.evops.screens.createevent.domain.model.CreateEventForm
import com.example.evops.screens.createevent.domain.repositories.CreateEventNetworkRepository

class CreateEventNetworkRepositoryImpl(private val createEventApi: CreateEventApi) :
    CreateEventNetworkRepository {
    override suspend fun getAuthorIds(): List<String> {
        return createEventApi.getUsers().authors.map { it.id }
    }

    override suspend fun createAuthor(userForm: CreateAuthorForm) {
        createEventApi.createUser(userForm.toData())
    }

    override suspend fun createEvent(
        eventForm: CreateEventForm,
        userId: String,
    ) {
        createEventApi.createEvent(eventForm.toData(userId))
    }
}
