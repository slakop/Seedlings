package com.example.seedlings.net

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import com.example.seedlings.chat.ChatItem
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

private const val baseUrl = "http://192.168.1.41:5000"

interface Api {
    @GET("get")
    suspend fun getProfile(): Response<List<ChatItem>>

    @POST("post")
    suspend fun setProfile(@Body profile: ChatItem): Response<ChatItem>

    @PUT("put")
    suspend fun putProfile(@Body profile: List<ChatItem>): Response<List<ChatItem>>

    @HTTP(method = "DELETE", path = "delete", hasBody = true)
    suspend fun delProfile(@Body profile: ChatItem): Response<ChatItem>
}

fun buildRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(baseUrl)
    .client(okHttpClient)
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .build()
