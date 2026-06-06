package com.codandotv.streamplayerapp.core.navigation.routes

import com.codandotv.streamplayerapp.core.navigation.routes.BottomNavRoutes.PARAM.PROFILE_ID

object BottomNavRoutes {
    const val HOME = "bottomHome"
    const val HOME_COMPLETE = "$HOME?$PROFILE_ID={$PROFILE_ID}"
    const val GAMES = "bottomGames"
    const val NEWS = "bottomNews"
    const val DOWNLOADS = "bottomDownloads"

    object PARAM {
        const val PROFILE_ID = "userId"
    }
}
