package ar.edu.unlam.mobile.scaffolding.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ar.edu.unlam.mobile.scaffolding.ui.feed.FeedScreen
import ar.edu.unlam.mobile.scaffolding.ui.tuit.create.CreateTuitScreen
import ar.edu.unlam.mobile.scaffolding.ui.tuit.draft.DraftTuitScreen
import ar.edu.unlam.mobile.scaffolding.ui.user.auth.login.LoginScreen
import ar.edu.unlam.mobile.scaffolding.ui.user.auth.register.RegisterScreen
import ar.edu.unlam.mobile.scaffolding.ui.user.favorite.FavoriteUsersScreen
import ar.edu.unlam.mobile.scaffolding.ui.user.profile.ProfileScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route,
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onNavigateToRegister = {
                    navController.navigate(Screen.Register.route)
                },
                onNavigateBack = {
                    navController.navigate(Screen.Login.route)
                },
                onLoginSuccess = {
                    navController.navigate(Screen.Feed.route) {
                        popUpTo(Screen.Login.route) {
                            inclusive = true
                        }
                    }
                },
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                onNavigateBack = {
                    navController.navigate(Screen.Login.route)
                },
                onNavigateBackErrorView = {
                    navController.navigate(Screen.Register.route)
                },
                onRegisterSuccess = {
                    navController.navigate(Screen.Feed.route) {
                        popUpTo(Screen.Register.route) {
                            inclusive = true
                        }
                    }
                },
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
                },
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
                },
            )
        }

        composable(
            route = Screen.CreateTuit.route + "?draftText={draftText}",
            arguments =
                listOf(
                    navArgument("draftText") {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                ),
        ) { backStackEntry ->
            val draftText = backStackEntry.arguments?.getString("draftText") ?: ""
            CreateTuitScreen(
                initialText = draftText,
                onDismissRequest = {
                    navController.navigateUp()
                },
                onCreateSuccess = {
                    navController.navigateUp()
                },
                onNavigateToDrafts = {
                    navController.navigate(Screen.DraftTuit.route)
                }
            )
        }

        composable(Screen.DraftTuit.route) {
            DraftTuitScreen(
                onDismissRequest = {
                    navController.navigateUp()
                },
                onNavigateToCreate = { draftText ->
                    navController.navigate(Screen.CreateTuit.route + "?draftText=$draftText")
                },
            )
        }
    }
}
