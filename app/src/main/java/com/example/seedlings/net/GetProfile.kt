package com.example.seedlings.net

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import com.example.seedlings.chat.ChatItem
import java.io.IOException
import javax.inject.Inject

interface GetProfile {
    suspend operator fun invoke(): List<ChatItem>

    class Impl @Inject constructor(private val api: Api) : GetProfile {
        override suspend fun invoke(): List<ChatItem> {
            //val response = api.getProfile(123)
            val response = api.getProfile()
            if (!response.isSuccessful) {
                throw IOException("Unexpected code $response")
            }
            return response.body() ?: throw IOException("Empty body $response")
        }
    }
}