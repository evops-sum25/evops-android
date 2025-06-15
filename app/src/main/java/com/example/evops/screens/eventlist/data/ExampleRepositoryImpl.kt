package com.example.evops.screens.eventlist.data

import com.example.evops.screens.eventlist.data.api.ExampleApi
import com.example.evops.screens.eventlist.data.dto.ExampleDto
import com.example.evops.screens.eventlist.domain.repository.ExampleRepository
import javax.inject.Inject

class ExampleRepositoryImpl @Inject constructor(
    private val api: ExampleApi
): ExampleRepository {
    override suspend fun createEvent(exampleDto: ExampleDto): ExampleDto {
        return api.createEvent(exampleDto)
    }
}