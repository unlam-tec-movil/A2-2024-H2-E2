package ar.edu.unlam.mobile.scaffolding.ui.user.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ar.edu.unlam.mobile.scaffolding.domain.model.FavoriteUser
import ar.edu.unlam.mobile.scaffolding.ui.components.FavoriteUsersList
import ar.edu.unlam.mobile.scaffolding.ui.core.component.error.ErrorView
import ar.edu.unlam.mobile.scaffolding.ui.core.component.loading.LoadingIndicator
import ar.edu.unlam.mobile.scaffolding.ui.core.state.UIState
import ar.edu.unlam.mobile.scaffolding.ui.core.state.onError
import ar.edu.unlam.mobile.scaffolding.ui.core.state.onLoading
import ar.edu.unlam.mobile.scaffolding.ui.core.state.onSuccess

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteUsersScreen(
    state: FavoriteUsersState,
    onDeleteUser: (FavoriteUser) -> Unit,
    onRetry: () -> Unit,
) {
    var userToDelete by remember { mutableStateOf<FavoriteUser?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Usuarios favoritos") },
            )
        },
    ) { paddingValues ->
        Box(modifier = Modifier.padding((paddingValues))) {
            state.favoriteUserState
                .onLoading {
                    LoadingIndicator()
                }.onSuccess { favoriteUsers ->
                    FavoriteUsersList(
                        favUsers = favoriteUsers,
                        onDeleteUser = { user ->
                            userToDelete = user
                        },
                    )
                }.onError { message ->
                    ErrorView(
                        message = message,
                        onRetry = onRetry,
                    )
                }

            userToDelete?.let { user ->
                ConfirmDelete(
                    user = user,
                    onConfirm = {
                        onDeleteUser(user)
                        userToDelete = null
                    },
                    onDismiss = {
                        userToDelete = null
                    },
                )
            }
        }
    }
}

@Composable
fun ConfirmDelete(
    user: FavoriteUser,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Elimminar Usuario") },
        text = { Text("¿Estás seguro de que quieres eliminar a ${user.name} de favoritos?") },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Eliminar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun FavoriteUserScreenPreview() {
    val users =
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
    FavoriteUsersScreen(
        state = FavoriteUsersState(
            favoriteUserState = UIState.Success(users)
        ),
        onRetry = {},
        onDeleteUser = {}
    )
}
