package com.example.seedlings.data

import com.example.seedlings.chat.ChatItem

sealed class ViewState {
    data object None : ViewState()
    data object Loading : ViewState()
    data class Content(val profile: List<ChatItem>) : ViewState()
    data class ContentSend(val profile: ChatItem) : ViewState()
    data class ContentAllSend(val profile: List<ChatItem>) : ViewState()
}