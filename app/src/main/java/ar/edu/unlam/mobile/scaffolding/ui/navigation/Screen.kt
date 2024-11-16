package ar.edu.unlam.mobile.scaffolding.ui.navigation

sealed class Screen(val route: String) {
    data object Login : Screen("login")

    data object Register : Screen("register")

    data object Feed : Screen("home")

    data object Profile : Screen("profile")

    data object FavoriteUsers : Screen("favorite_users")

    data object CreateTuit : Screen("create_tuit") {
        fun createRoute(draftText: String = "", draftId: Int? = null): String {
            return when {
                draftText.isEmpty() && draftId == null -> route
                draftId == null -> "$route?draftText=$draftText"
                else -> "$route?draftText=$draftText&draftId"
            }
        }
    }

    data object DraftTuit : Screen("draft_tuit")
}
