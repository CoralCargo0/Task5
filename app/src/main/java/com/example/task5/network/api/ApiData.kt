package com.example.task5.network.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiData(
    @field:Json(name = "url") val imageUrl: String?
)
