package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ar.edu.unlam.mobile.scaffolding.domain.model.FavoriteUser

@Composable
fun FavoriteUsersList(
    favUsers: List<FavoriteUser>,
    onDeleteUser: (FavoriteUser) -> Unit,
) {
    LazyColumn {
        items(favUsers) { user ->
            FavoriteUserCard(
                favoriteUser = user,
                onDeleteUser = { onDeleteUser(user) },
            )
        }
    }
}

@Preview
@Composable
fun FavoriteUsersListPreview() {
    val favUsers =
        listOf(
            FavoriteUser(
                name = "Usuario 1",
                avatarUrl = "https://static-00.iconduck.com/assets.00/profile-default-icon-512x511-v4sw4m29.png",
            ),
            FavoriteUser(
                name = "Usuario 2",
                avatarUrl = "https://static-00.iconduck.com/assets.00/profile-default-icon-512x511-v4sw4m29.png",
            ),
            FavoriteUser(
                name = "Usuario 3",
                avatarUrl = "https://static-00.iconduck.com/assets.00/profile-default-icon-512x511-v4sw4m29.png",
            ),
            FavoriteUser(
                name = "Usuario con nombre largo a ver como queda",
                avatarUrl = "https://static-00.iconduck.com/assets.00/profile-default-icon-512x511-v4sw4m29.png",
            ),
        )
    FavoriteUsersList(
        favUsers,
        onDeleteUser = {},
    )
}
