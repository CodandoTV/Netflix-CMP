package com.codandotv.streamplayerapp.feature.profile.domain

import com.codandotv.streamplayerapp.feature.profile.data.model.ProfilesResponse

fun com.codandotv.streamplayerapp.feature.profile.data.model.ProfilesResponse.toProfiles(): List<com.codandotv.streamplayerapp.feature.profile.domain.ProfileStream> = this.profiles.map { profileResponse ->
    _root_ide_package_.com.codandotv.streamplayerapp.feature.profile.domain.ProfileStream(
        id = profileResponse.id,
        name = profileResponse.name,
        imageUrl = profileResponse.profile_url,
    )
}
