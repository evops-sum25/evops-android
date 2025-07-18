package com.example.evops.core.common

object Config {
    private const val DEV_URL = "http://10.0.2.2:8080"
    private const val PROD_URL = "http://31.59.170.53:8080"
    const val BASE_URL = DEV_URL
    const val EVENT_PAGE_LIMIT = 20

    fun constructImageUrl(imageId: String): String {
        return "$BASE_URL/v1/events/images/$imageId"
    }
}
