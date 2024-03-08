package com.example.storytales.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DefaultError(
    @SerialName("message")
    val message:String
)
