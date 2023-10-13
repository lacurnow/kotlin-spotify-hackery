package org.starter.model

import kotlinx.serialization.Serializable

@Serializable
data class Pong(
    val message: String,
    val number: Int
)
