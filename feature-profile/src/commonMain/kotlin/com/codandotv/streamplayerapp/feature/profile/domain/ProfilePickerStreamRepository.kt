package com.codandotv.streamplayerapp.feature.profile.domain

import kotlinx.coroutines.flow.Flow

interface ProfilePickerStreamRepository {
    suspend fun getProfiles(): Flow<List<ProfileStream>>
}
