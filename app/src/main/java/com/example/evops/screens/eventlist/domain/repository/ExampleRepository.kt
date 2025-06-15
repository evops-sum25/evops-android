package com.example.evops.screens.eventlist.domain.repository

import com.example.evops.screens.eventlist.data.dto.ExampleDto

interface ExampleRepository {
    suspend fun createEvent(exampleDto: ExampleDto): ExampleDto
}