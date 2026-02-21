package com.example.seedlings.net

import kotlinx.coroutines.delay
import kotlinx.datetime.Instant
import com.example.seedlings.chat.ChatItem
import javax.inject.Inject

interface NetService {
    suspend fun getProfile(): List<ChatItem>
    suspend fun setProfile(profile: ChatItem): ChatItem
    suspend fun putProfile(profile: List<ChatItem>): List<ChatItem>
    suspend fun delProfile(profile: ChatItem): ChatItem


    class Impl @Inject constructor(
        private val profileCommand: GetProfile,
        private val setProfileCommand: SetProfile,
        private val putProfileCommand: PutProfile,
        private val delProfileCommand: DelProfile
      ) : NetService {
        override suspend fun getProfile(): List<ChatItem> = profileCommand()
        override suspend fun setProfile(profile: ChatItem): ChatItem = setProfileCommand(profile)
        override suspend fun putProfile(profile: List<ChatItem>): List<ChatItem> = putProfileCommand(profile)
        override suspend fun delProfile(profile: ChatItem): ChatItem = delProfileCommand(profile)
    }
}

