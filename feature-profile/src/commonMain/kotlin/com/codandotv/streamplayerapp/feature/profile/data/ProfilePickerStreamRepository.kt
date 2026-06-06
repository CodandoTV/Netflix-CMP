package com.codandotv.streamplayerapp.feature.profile.data

import com.codandotv.streamplayerapp.core_networking.handleError.toFlow
import com.codandotv.streamplayerapp.core_networking.handleError.toResult
import com.codandotv.streamplayerapp.feature.profile.domain.ProfileStream
import com.codandotv.streamplayerapp.feature.profile.domain.toProfiles
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

interface ProfilePickerStreamRepository {
    suspend fun getProfiles(): Flow<List<com.codandotv.streamplayerapp.feature.profile.domain.ProfileStream>>
}

@Factory
class ProfilePickerStreamRepositoryImpl(
    private val service: com.codandotv.streamplayerapp.feature.profile.data.ProfilePickerStreamService
) : com.codandotv.streamplayerapp.feature.profile.data.ProfilePickerStreamRepository {

    override suspend fun getProfiles(): Flow<List<com.codandotv.streamplayerapp.feature.profile.domain.ProfileStream>> {

        with(service.getProfiles()) {
            if (this.toResult().isFailure || this.toResult().getOrNull() == null) {
                // Log.i("ProfilePickerStreamRepositoryImpl", "versão off carregada")
                return flowOf(_root_ide_package_.com.codandotv.streamplayerapp.feature.profile.data.mockProfiles)
            } else {
                return this.toFlow().map { it.toProfiles() }
            }
        }
    }
}


private val mockProfiles = listOf(
    _root_ide_package_.com.codandotv.streamplayerapp.feature.profile.domain.ProfileStream(
        id = "1",
        name = "Chapei de Palha",
        imageUrl = "https://raw.githubusercontent.com/git-jr/sample-files/main/profile%20pics/netflix_profile_pic_1.png"
    ),
    _root_ide_package_.com.codandotv.streamplayerapp.feature.profile.domain.ProfileStream(
        id = "2",
        name = "Martha",
        imageUrl = "https://raw.githubusercontent.com/git-jr/sample-files/main/profile%20pics/netflix_profile_pic_2.png"
    ),
    _root_ide_package_.com.codandotv.streamplayerapp.feature.profile.domain.ProfileStream(
        id = "3",
        name = "Morningstar",
        imageUrl = "https://raw.githubusercontent.com/git-jr/sample-files/main/profile%20pics/netflix_profile_pic_3.png"
    ),
    _root_ide_package_.com.codandotv.streamplayerapp.feature.profile.domain.ProfileStream(
        id = "4",
        name = "Shelby",
        imageUrl = "https://raw.githubusercontent.com/git-jr/sample-files/main/profile%20pics/netflix_profile_pic_4.png"
    ),
    _root_ide_package_.com.codandotv.streamplayerapp.feature.profile.domain.ProfileStream(
        id = "5",
        name = "Mooncake",
        imageUrl = "https://raw.githubusercontent.com/git-jr/sample-files/main/profile%20pics/netflix_profile_pic_5.png"
    ),
    _root_ide_package_.com.codandotv.streamplayerapp.feature.profile.domain.ProfileStream(
        id = "6",
        name = "CodandoTV",
        imageUrl = "https://raw.githubusercontent.com/git-jr/sample-files/main/profile%20pics/netflix_profile_pic_6.png"
    )
)
