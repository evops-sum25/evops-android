package com.example.evops.screens.eventlist.domain.model

import java.time.LocalDate

data class EventItemData(
    val title: String,
    val eventPublisherData: EventItemPublisherData,
    val imageUrl: String,
    val attendeesCount: UInt,
    val place: String,
    val date: LocalDate,
)