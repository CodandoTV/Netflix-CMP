package com.codandotv.streamplayerapp.feature.profile.data

import com.codandotv.streamplayerapp.core.networking.handleError.NetworkResponse
import com.codandotv.streamplayerapp.core.networking.handleError.safeRequest
import com.codandotv.streamplayerapp.feature.profile.data.model.ProfilesResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.url
import org.koin.core.annotation.Factory

interface ProfilePickerStreamService {
    suspend fun getProfiles(): NetworkResponse<ProfilesResponse>
}

@Factory
class ProfilePickerStreamServiceImpl(
    private val client: HttpClient
) : ProfilePickerStreamService {
    override suspend fun getProfiles(): NetworkResponse<ProfilesResponse> =
        client.safeRequest {
            url("profiles")
        }
}
