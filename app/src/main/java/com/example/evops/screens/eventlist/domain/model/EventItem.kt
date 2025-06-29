package com.example.evops.screens.eventlist.domain.model

import java.time.LocalDate

data class EventItem(
    val id: String,
    val title: String,
    val author: EventItemAuthor,
    val imageUrl: String,
    val attendeesCount: UInt,
    val place: String,
    val date: LocalDate,
)
