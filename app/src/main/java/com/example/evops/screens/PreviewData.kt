package com.example.evops.screens

import com.example.evops.screens.evendetails.domain.model.EventDetails
import com.example.evops.screens.evendetails.domain.model.EventDetailsPublisher
import com.example.evops.screens.evendetails.domain.model.EventDetailsTag
import com.example.evops.screens.eventlist.domain.model.EventItem
import com.example.evops.screens.eventlist.domain.model.EventItemPublisher
import java.time.LocalDate

object PreviewData {
    val eventItemPublisher = EventItemPublisher(
        name = "BDSM",
        avatarPreviewUrl = "https://meowle.fintech-qa.ru/photos/image-1745661496544.jpg"
    )
    val eventItem = EventItem(
        id = "",
        title = "Probstat Final Preparation",
        eventPublisherData = eventItemPublisher,
        imageUrl = "https://meowle.fintech-qa.ru/photos/image-1745661496544.jpg",
        attendeesCount = 16u,
        place = "IU 108",
        date = LocalDate.now(),
    )

    val eventDetailsPublisher = EventDetailsPublisher(
        name = "BDSM",
        avatarPreviewUrl = "https://meowle.fintech-qa.ru/photos/image-1745661496544.jpg"
    )
    val eventDetails = EventDetails(
        eventDetailsPublisher = eventDetailsPublisher,
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
        tagsData = listOf(EventDetailsTag("BDSM"), EventDetailsTag("study"), EventDetailsTag("math"), EventDetailsTag("B23")),
    )
}