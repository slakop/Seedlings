package com.example.seedlings

import javax.inject.Inject

interface SessionManager {
    fun getToken(): String

    class Impl @Inject constructor() : SessionManager {
        override fun getToken(): String = "ABCD:12345:DEFG"
    }
}