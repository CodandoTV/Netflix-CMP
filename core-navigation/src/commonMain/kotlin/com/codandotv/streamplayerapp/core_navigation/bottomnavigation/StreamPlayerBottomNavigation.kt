package com.codandotv.streamplayerapp.core_navigation.bottomnavigation

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.codandotv.streamplayerapp.core_navigation.helper.currentRoute
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

private val bottomMenuItems = listOf(
    BottomNavItem.Home,
    BottomNavItem.News,
    BottomNavItem.Games,
    BottomNavItem.Downloads
)

@Composable
fun StreamPlayerBottomNavigation(navController: NavController) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.height(72.dp)
    ) {
        val currentRoute = currentRoute(navController = navController)

        bottomMenuItems.forEach { item ->
            NavigationBarItem(colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.onBackground,
                unselectedIconColor = MaterialTheme.colorScheme.surfaceVariant,
                selectedTextColor = MaterialTheme.colorScheme.onBackground,
                unselectedTextColor = MaterialTheme.colorScheme.surfaceVariant,
                indicatorColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                    LocalAbsoluteTonalElevation.current
                )
            ),
                icon = { NavItemIcon(currentRoute, item) },
                label = {
                    Text(
                        text = stringResource(item.title),
                        style = MaterialTheme.typography.bodySmall,
                    )
                },
                selected = currentRoute == item.screenRoute,
                onClick = { onItemClicked(navController, item) })
        }
    }
}

@Composable
private fun NavItemIcon(
    currentRoute: String?,
    item: BottomNavItem
) {
    Icon(
        painterResource(if (currentRoute == item.screenRoute || currentRoute?.contains(item.screenRoute) == true){
            item.iconSelected
        } else {
            item.iconUnselected
        }),
        contentDescription = stringResource(item.title),
    )
}

private fun onItemClicked(
    navController: NavController, item: BottomNavItem
) {
    navController.navigate(item.screenRoute) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}
