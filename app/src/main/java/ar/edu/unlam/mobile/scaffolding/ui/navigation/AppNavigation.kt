package ar.edu.unlam.mobile.scaffolding.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ar.edu.unlam.mobile.scaffolding.ui.feed.FeedScreen
import ar.edu.unlam.mobile.scaffolding.ui.tuit.create.CreateTuitScreen
import ar.edu.unlam.mobile.scaffolding.ui.user.auth.login.LoginScreen
import ar.edu.unlam.mobile.scaffolding.ui.user.auth.register.RegisterScreen
import ar.edu.unlam.mobile.scaffolding.ui.user.favorite.FavoriteUsersScreen
import ar.edu.unlam.mobile.scaffolding.ui.user.profile.ProfileScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onNavigateToRegister = {
                    navController.navigate(Screen.Register.route)
                },
                onLoginSuccess = {
                    navController.navigate(Screen.Feed.route) {
                        popUpTo(Screen.Login.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                onNavigateBack = {
                    navController.navigateUp()
                },
                onRegisterSuccess = {
                    navController.navigate(Screen.Feed.route) {
                        popUpTo(Screen.Register.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(Screen.Feed.route) {
            FeedScreen(
                onNavigateToCreateTuit = {
                    navController.navigate(Screen.CreateTuit.route)
                },
                onLogout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Feed.route) {
                            inclusive = true
                        }
                    }
                },
                onNavigateToDrafts = {
                    navController.navigate(Screen.DraftTuit.route)
                }
            )
        }

        composable(Screen.Profile.route) {
            ProfileScreen(
                onLogout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Feed.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(Screen.FavoriteUsers.route) {
            FavoriteUsersScreen(
                onLogout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Feed.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(Screen.CreateTuit.route) {
            CreateTuitScreen(
                onDismissRequest = {
                    navController.navigateUp()
                },
                onCreateSuccess = {
                    navController.navigateUp()
                }
            )
        }

        composable(Screen.DraftTuit.route) {
            DrafTuitScreen(
                onDisMissRequest = {
                    navController.navigateUp()
                }
            )
        }
    }
}
