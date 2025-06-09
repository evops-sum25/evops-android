package com.example.evops.screens

import com.example.evops.screens.evendetails.domain.model.EventDetailsData
import com.example.evops.screens.evendetails.domain.model.EventDetailsPublisherData
import com.example.evops.screens.evendetails.domain.model.TagData
import com.example.evops.screens.eventlist.domain.model.EventData
import com.example.evops.screens.eventlist.domain.model.EventPublisherData
import java.time.LocalDate

object PreviewData {
    val eventPublisherData = EventPublisherData(
        name = "BDSM",
        avatarPreviewUrl = "https://meowle.fintech-qa.ru/photos/image-1745661496544.jpg"
    )
    val eventData = EventData(
        title = "Probstat Final Preparation",
        eventPublisherData = eventPublisherData,
        eventImageUrl = "https://meowle.fintech-qa.ru/photos/image-1745661496544.jpg",
        attendeesCount = 16u,
        commentsCount = 4u,
        place = "IU 108",
        date = LocalDate.now(),
    )

    val eventDetailsPublisherData = EventDetailsPublisherData(
        name = "BDSM",
        avatarPreviewUrl = "https://meowle.fintech-qa.ru/photos/image-1745661496544.jpg"
    )
    val eventDetailsData = EventDetailsData(
        title = "Probstat Final Preparation",
        eventDetailsPublisherData = eventDetailsPublisherData,
        eventImageUrls = listOf(
            "https://meowle.fintech-qa.ru/photos/image-1745661496544.jpg",
            "https://meowle.fintech-qa.ru/photos/image-1745661496544.jpg",
        ),
        attendeesCount = 16u,
        commentsCount = 4u,
        place = "IU 108",
        date = LocalDate.now(),
        tagsData = listOf(TagData("BDSM"), TagData("study"), TagData("math"), TagData("B23")),
    )
}