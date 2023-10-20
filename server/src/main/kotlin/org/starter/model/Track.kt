package org.starter.model

import kotlinx.serialization.Serializable

@Serializable
data class Track(
    val id: String?,
    val track_name: String,
    val artist_name: String,
    val streams: Int
)