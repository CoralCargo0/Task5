package com.example.task5.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiData(
    @field:Json(name = "url") val imageUrl: String?
    // @Json(name = "url") val imageUrl: String?
)
