package com.codandotv.streamplayerapp.feature.profile.data

import com.codandotv.streamplayerapp.core_networking.handleError.NetworkResponse
import com.codandotv.streamplayerapp.core_networking.handleError.safeRequest
import com.codandotv.streamplayerapp.feature.profile.data.model.ProfilesResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.url
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Provided

interface ProfilePickerStreamService {
    suspend fun getProfiles(): NetworkResponse<com.codandotv.streamplayerapp.feature.profile.data.model.ProfilesResponse>
}

@Factory
class ProfilePickerStreamServiceImpl(
    @Provided private val client: HttpClient
) : com.codandotv.streamplayerapp.feature.profile.data.ProfilePickerStreamService {
    override suspend fun getProfiles(): NetworkResponse<com.codandotv.streamplayerapp.feature.profile.data.model.ProfilesResponse> =
        client.safeRequest {
            url("profiles")
        }
}
