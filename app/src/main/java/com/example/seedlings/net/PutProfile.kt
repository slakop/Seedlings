package com.example.seedlings.net

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import com.example.seedlings.chat.ChatItem
import java.io.IOException
import javax.inject.Inject

interface PutProfile {
    suspend operator fun invoke(profile: List<ChatItem>): List<ChatItem>

    class Impl @Inject constructor(private val api: Api) : PutProfile {
        override suspend fun invoke(profile: List<ChatItem>): List<ChatItem> {
            val response = api.putProfile(profile)
            if (!response.isSuccessful) {
                throw IOException("Unexpected code $response")
            }
            return response.body() ?: throw IOException("Empty body $response")
        }
    }
}