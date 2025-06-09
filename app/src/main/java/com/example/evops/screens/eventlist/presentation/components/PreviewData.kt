package com.example.evops.screens.eventlist.presentation.components

import com.example.evops.screens.eventlist.domain.model.EventData
import com.example.evops.screens.eventlist.domain.model.PublisherData
import java.time.LocalDate

object PreviewData {
    val publisherData = PublisherData(
        name = "BDSM",
        avatarPreviewUrl = "https://meowle.fintech-qa.ru/photos/image-1745661496544.jpg"
    )
    val eventData = EventData(
        title = "Probstat Final Preparation",
        publisherData = publisherData,
        eventImageUrl = "https://meowle.fintech-qa.ru/photos/image-1745661496544.jpg",
        attendeesCount = 16u,
        commentsCount = 4u,
        place = "IU 108",
        date = LocalDate.now(),
    )
}