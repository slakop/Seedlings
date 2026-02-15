package com.example.seedlings.net

import kotlinx.coroutines.delay
import kotlinx.datetime.Instant
import com.example.seedlings.data.Profile
import javax.inject.Inject

interface NetService {
    suspend fun getProfile(): List<Profile>
    suspend fun setProfile(profile: Profile): Profile
    suspend fun putProfile(profile: List<Profile>): List<Profile>
    suspend fun delProfile(profile: Profile): Profile


    class Impl @Inject constructor(
        private val profileCommand: GetProfile,
        private val setProfileCommand: SetProfile,
        private val putProfileCommand: PutProfile,
        private val delProfileCommand: DelProfile
      ) : NetService {
        override suspend fun getProfile(): List<Profile> = profileCommand()
        override suspend fun setProfile(profile: Profile): Profile = setProfileCommand(profile)
        override suspend fun putProfile(profile: List<Profile>): List<Profile> = putProfileCommand(profile)
        override suspend fun delProfile(profile: Profile): Profile = delProfileCommand(profile)
    }
}

