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
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.domain.model.FavoriteUser
import ar.edu.unlam.mobile.scaffolding.ui.components.FavoriteUsersList
import ar.edu.unlam.mobile.scaffolding.ui.components.MainTopAppBar
import ar.edu.unlam.mobile.scaffolding.ui.core.component.error.ErrorView
import ar.edu.unlam.mobile.scaffolding.ui.core.component.loading.LoadingIndicator
import ar.edu.unlam.mobile.scaffolding.ui.core.state.onError
import ar.edu.unlam.mobile.scaffolding.ui.core.state.onLoading
import ar.edu.unlam.mobile.scaffolding.ui.core.state.onSuccess
import ar.edu.unlam.mobile.scaffolding.ui.user.auth.logout.LogoutViewModel

@Composable
fun FavoriteUsersScreen(
    viewModel: FavoriteUsersViewModel = hiltViewModel(),
    logoutViewModel: LogoutViewModel = hiltViewModel(),
    onLogout: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var userToDelete by remember { mutableStateOf<FavoriteUser?>(null) }

    Scaffold(
        topBar = {
            MainTopAppBar(
                title = stringResource(R.string.favorite_users_title),
                onLogout = {
                    logoutViewModel.logout()
                    onLogout()
                },
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
                        onRetry = { viewModel.retryLoadFavoriteUsers() },
                    )
                }

            userToDelete?.let { user ->
                ConfirmDelete(
                    user = user,
                    onConfirm = {
                        viewModel.removeFavoriteUsers(user)
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
        title = { Text(stringResource(R.string.delete_user_title)) },
        text = {
            Text(
                stringResource(
                    R.string.delete_user_confirmation,
                    user.name,
                ),
            )
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(stringResource(R.string.delete))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.cancel))
            }
        },
    )
}
