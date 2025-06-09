package com.example.evops.screens.evendetails.domain.model

import java.time.LocalDate

data class EventDetailsData(
    val title: String,
    val eventDetailsPublisherData: EventDetailsPublisherData,
    val eventImageUrls: List<String>,
    val attendeesCount: UInt,
    val commentsCount: UInt,
    val place: String,
    val date: LocalDate,
    val tagsData: List<TagData>,
) // TODO("add fields for description and reactions")
