package com.example.fragmentsnew.data.retrofit.chuckIO

import com.squareup.moshi.Json

data class JokesDataClass(
    @Json(name = "icon_url")
    val iconUrl: String,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "updated_at")
    val updatedAt: String,
    val id: String,
    val url: String,
    @Json(name = "value")
    val value: String
)
