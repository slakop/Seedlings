package com.example.seedlings

data class ChatItem (
    val id: Int,
    val title: String,
    val author: String,
    val message: String,
    val verifiedIcon: Boolean,
    val muteIcon: Boolean,
    val scamPatch: Boolean,
    val counter: Int,
    val check: Boolean,
    val read: Boolean,
    val time: String,
)
