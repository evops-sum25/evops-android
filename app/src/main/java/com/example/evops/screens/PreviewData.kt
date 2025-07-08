package com.example.evops.screens

import com.example.evops.screens.evendetails.domain.model.EventDetails
import com.example.evops.screens.evendetails.domain.model.EventDetailsAuthor
import com.example.evops.screens.evendetails.domain.model.EventDetailsTag
import com.example.evops.screens.eventlist.domain.model.EventItem
import com.example.evops.screens.eventlist.domain.model.EventItemAuthor
import java.time.LocalDate

object PreviewData {
    val eventItemAuthor = EventItemAuthor(id = "", name = "BDSM")
    val eventItem =
        EventItem(
            id = "",
            title = "Probstat Final Preparation",
            author = eventItemAuthor,
            imageUrl = "https://meowle.fintech-qa.ru/photos/image-1745661496544.jpg",
            attendeesCount = 16u,
            place = "IU 108",
            date = LocalDate.now(),
        )

    val eventDetailsAuthor = EventDetailsAuthor(id = "", name = "BDSM")
    val eventDetails =
        EventDetails(
            author = eventDetailsAuthor,
            imageUrls =
                listOf(
                    "https://meowle.fintech-qa.ru/photos/image-1745661496544.jpg",
                    "https://meowle.fintech-qa.ru/photos/image-1745661496544.jpg",
                ),
            title = "Probstat Final Preparation",
            description =
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec molestie eu " +
                    "sapien vel auctor. Vivamus non ante venenatis, vestibulum enim id, vestibulum " +
                    "augue. Donec lacus felis, efficitur non dolor ornare, fermentum malesuada est. " +
                    "Cras interdum lectus sed justo sollicitudin, id porta nisl consequat. Ut maximus " +
                    "viverra est nec rutrum.",
            attendeesCount = 16u,
            place = "IU 108",
            date = LocalDate.now(),
            tagsData =
                listOf(
                    EventDetailsTag("BDSM"),
                    EventDetailsTag("study"),
                    EventDetailsTag("math"),
                    EventDetailsTag("B23"),
                ),
        )
}
