package ar.edu.unlam.mobile.scaffolding.ui.navigation

sealed class Screen(val route: String) {
    data object Login : Screen("login")

    data object Register : Screen("register")

    data object Feed : Screen("home")

    data object Profile : Screen("profile")

    data object FavoriteUsers : Screen("favorite_users")

    data object CreateTuit : Screen("create_tuit")

    data object DraftTuit : Screen("draft_tuit")
}
