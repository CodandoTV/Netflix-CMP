package com.codandotv.streamplayerapp.core_navigation.bottomnavigation

import com.codandotv.streamplayerapp.core_navigation.routes.BottomNavRoutes
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import streamplayerapp_kmp.core_navigation.generated.resources.Res
import streamplayerapp_kmp.core_navigation.generated.resources.bottom_nav_downloads
import streamplayerapp_kmp.core_navigation.generated.resources.bottom_nav_games
import streamplayerapp_kmp.core_navigation.generated.resources.bottom_nav_home
import streamplayerapp_kmp.core_navigation.generated.resources.bottom_nav_news
import streamplayerapp_kmp.core_navigation.generated.resources.ic_downloads_selected
import streamplayerapp_kmp.core_navigation.generated.resources.ic_downloads_unselected
import streamplayerapp_kmp.core_navigation.generated.resources.ic_games_selected
import streamplayerapp_kmp.core_navigation.generated.resources.ic_games_unselected
import streamplayerapp_kmp.core_navigation.generated.resources.ic_home_selected
import streamplayerapp_kmp.core_navigation.generated.resources.ic_home_unselected
import streamplayerapp_kmp.core_navigation.generated.resources.ic_news_selected
import streamplayerapp_kmp.core_navigation.generated.resources.ic_news_unselected

sealed class BottomNavItem(
    val title: StringResource,
    val iconUnselected: DrawableResource,
    val iconSelected: DrawableResource,
    val screenRoute: String
) {
    object Home :
        BottomNavItem(
            Res.string.bottom_nav_home,
            Res.drawable.ic_home_unselected,
            Res.drawable.ic_home_selected,
            BottomNavRoutes.HOME
        )

    object Games :
        BottomNavItem(
            Res.string.bottom_nav_games,
            Res.drawable.ic_games_unselected,
            Res.drawable.ic_games_selected,
            BottomNavRoutes.GAMES
        )

    object News :
        BottomNavItem(
            Res.string.bottom_nav_news,
            Res.drawable.ic_news_unselected,
            Res.drawable.ic_news_selected,
            BottomNavRoutes.NEWS
        )

    object Downloads : BottomNavItem(
        Res.string.bottom_nav_downloads,
        Res.drawable.ic_downloads_unselected,
        Res.drawable.ic_downloads_selected,
        BottomNavRoutes.DOWNLOADS
    )
}
