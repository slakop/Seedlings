package com.example.seedlings.data

sealed class ViewState {
    data object None : ViewState()
    data object Loading : ViewState()
    data class Content(val profile: List<Profile>) : ViewState()
    data class ContentSend(val profile: Profile) : ViewState()
    data class ContentAllSend(val profile: List<Profile>) : ViewState()
}