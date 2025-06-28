package com.example.evops.screens.evendetails.domain.model

import java.time.LocalDate

data class EventDetails(
    val author: EventDetailsAuthor,
    val eventImageUrls: List<String>,
    val title: String,
    val description: String,
    val attendeesCount: UInt,
    val place: String,
    val date: LocalDate,
    val tagsData: List<EventDetailsTag>,
) // TODO("add fields for reactions")
