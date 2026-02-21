package com.example.seedlings.chat

data class ChatItem (
    val id: Int,
    val type: String,
    val name: String,
    val manufacturer: String,
    val day: Int,
    val planted: Boolean,
    val counter: Int,
    val date: String,
)
