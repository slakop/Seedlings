package com.example.seedlings.chat

import kotlinx.datetime.Instant
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
data class ChatItem (
    val id: Int,
    val type: String,
    val name: String,
    val manufacturer: String,
    val day: Int,
    val planted: Int,
    val counter: Int,
    val date: String,
)
