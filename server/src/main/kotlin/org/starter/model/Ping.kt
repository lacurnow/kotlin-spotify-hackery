package org.starter.model

import kotlinx.serialization.Serializable

@Serializable
data class Ping(
    val message: String,
    val number: Int
)
