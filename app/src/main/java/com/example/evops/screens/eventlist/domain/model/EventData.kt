package com.example.evops.screens.eventlist.domain.model

import java.time.LocalDate

data class EventData(
    val title: String,
    val publisherData: PublisherData,
    val eventImageUrl: String,
    val attendeesCount: UInt,
    val commentsCount: UInt,
    val place: String,
    val date: LocalDate,
)