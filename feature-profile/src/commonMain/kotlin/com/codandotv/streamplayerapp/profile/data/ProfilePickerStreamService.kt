package com.codandotv.streamplayerapp.profile.data

import com.codandotv.streamplayerapp.core_networking.handleError.NetworkResponse
import com.codandotv.streamplayerapp.core_networking.handleError.safeRequest
import com.codandotv.streamplayerapp.profile.data.model.ProfilesResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.url
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Provided

interface ProfilePickerStreamService {
    suspend fun getProfiles(): NetworkResponse<ProfilesResponse>
}

@Factory
class ProfilePickerStreamServiceImpl(
    @Provided private val client: HttpClient
) : ProfilePickerStreamService {
    override suspend fun getProfiles(): NetworkResponse<ProfilesResponse> =
        client.safeRequest {
            url("profiles")
        }
}
