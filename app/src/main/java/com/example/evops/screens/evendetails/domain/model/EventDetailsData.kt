package com.example.evops.screens.evendetails.domain.model

import java.time.LocalDate

data class EventDetailsData(
    val eventDetailsPublisherData: EventDetailsPublisherData,
    val eventImageUrls: List<String>,
    val title: String,
    val description: String,
    val attendeesCount: UInt,
    val place: String,
    val date: LocalDate,
    val tagsData: List<TagData>,
) // TODO("add fields for description and reactions")
