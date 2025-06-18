package com.example.evops.screens

import com.example.evops.screens.evendetails.domain.model.EventDetailsData
import com.example.evops.screens.evendetails.domain.model.EventDetailsPublisherData
import com.example.evops.screens.evendetails.domain.model.TagData
import com.example.evops.screens.eventlist.domain.model.EventItemData
import com.example.evops.screens.eventlist.domain.model.EventItemPublisherData
import java.time.LocalDate

object PreviewData {
    val eventItemPublisherData = EventItemPublisherData(
        name = "BDSM",
        avatarPreviewUrl = "https://meowle.fintech-qa.ru/photos/image-1745661496544.jpg"
    )
    val eventItemData = EventItemData(
        title = "Probstat Final Preparation",
        eventPublisherData = eventItemPublisherData,
        imageUrl = "https://meowle.fintech-qa.ru/photos/image-1745661496544.jpg",
        attendeesCount = 16u,
        place = "IU 108",
        date = LocalDate.now(),
    )

    val eventDetailsPublisherData = EventDetailsPublisherData(
        name = "BDSM",
        avatarPreviewUrl = "https://meowle.fintech-qa.ru/photos/image-1745661496544.jpg"
    )
    val eventDetailsData = EventDetailsData(
        eventDetailsPublisherData = eventDetailsPublisherData,
        eventImageUrls = listOf(
            "https://meowle.fintech-qa.ru/photos/image-1745661496544.jpg",
            "https://meowle.fintech-qa.ru/photos/image-1745661496544.jpg",
        ),
        title = "Probstat Final Preparation",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec molestie eu " +
                "sapien vel auctor. Vivamus non ante venenatis, vestibulum enim id, vestibulum " +
                "augue. Donec lacus felis, efficitur non dolor ornare, fermentum malesuada est. " +
                "Cras interdum lectus sed justo sollicitudin, id porta nisl consequat. Ut maximus " +
                "viverra est nec rutrum.",
        attendeesCount = 16u,
        place = "IU 108",
        date = LocalDate.now(),
        tagsData = listOf(TagData("BDSM"), TagData("study"), TagData("math"), TagData("B23")),
    )
}