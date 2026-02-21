package com.example.seedlings.net

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import com.example.seedlings.chat.ChatItem
import java.io.IOException
import javax.inject.Inject

interface DelProfile {
    suspend operator fun invoke(profile: ChatItem): ChatItem

    class Impl @Inject constructor(private val api: Api) : DelProfile {
        override suspend fun invoke(profile: ChatItem): ChatItem {
            val response = api.delProfile(profile)
            if (!response.isSuccessful) {
                throw IOException("Unexpected code $response")
            }
            return response.body() ?: throw IOException("Empty body $response")
        }
    }
}