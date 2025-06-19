package com.example.evops.screens.eventlist.domain.model

import java.time.LocalDate

data class EventItem(
    val title: String,
    val eventPublisherData: EventItemPublisher,
    val imageUrl: String,
    val attendeesCount: UInt,
    val place: String,
    val date: LocalDate,
)