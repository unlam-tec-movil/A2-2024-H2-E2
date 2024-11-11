package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ar.edu.unlam.mobile.scaffolding.ui.navigation.Screen

@Composable
fun BottomNavigationBar(controller: NavHostController) {
    val navBackStackEntry by controller.currentBackStackEntryAsState()

    NavigationBar {
        NavigationBarItem(
            selected = navBackStackEntry?.destination?.hierarchy?.any { it.route == Screen.Feed.route } == true,
            onClick = {
                controller.navigate(Screen.Feed.route)
                {
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = MaterialTheme.colorScheme.primary,
                )
            },
        )
        NavigationBarItem(
            selected = navBackStackEntry?.destination?.hierarchy?.any { it.route == Screen.Profile.route } == true,
            onClick = {
                controller.navigate(Screen.Profile.route)
                {
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile",
                    tint = MaterialTheme.colorScheme.primary,
                )
            },
        )
        NavigationBarItem(
            selected = navBackStackEntry?.destination?.hierarchy?.any { it.route == Screen.FavoriteUsers.route } == true,
            onClick = {
                controller.navigate(Screen.FavoriteUsers.route)
                {
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite Users",
                    tint = MaterialTheme.colorScheme.primary,
                )
            },
        )
    }
}
