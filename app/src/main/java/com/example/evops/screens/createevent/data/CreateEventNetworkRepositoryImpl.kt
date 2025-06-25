package com.example.evops.screens.createevent.data

import com.example.evops.screens.createevent.data.api.CreateEventApi
import com.example.evops.screens.createevent.data.mappers.CreateEventMapper.toData
import com.example.evops.screens.createevent.domain.model.CreateEventForm
import com.example.evops.screens.createevent.domain.model.CreateUserForm
import com.example.evops.screens.createevent.domain.repository.CreateEventNetworkRepository

class CreateEventNetworkRepositoryImpl(private val createEventApi: CreateEventApi) : CreateEventNetworkRepository {
    override suspend fun getUserIds(): List<String> {
        return createEventApi.getUsers().users.map { it.id }
    }

    override suspend fun createUser(userForm: CreateUserForm) {
        createEventApi.createUser(userForm.toData())
    }

    override suspend fun createEvent(eventForm: CreateEventForm) {
        createEventApi.createEvent(eventForm.toData())
    }
}