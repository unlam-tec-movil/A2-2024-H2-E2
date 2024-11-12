package ar.edu.unlam.mobile.scaffolding.ui.user.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.domain.model.Profile
import ar.edu.unlam.mobile.scaffolding.ui.components.MainTopAppBar
import ar.edu.unlam.mobile.scaffolding.ui.core.component.error.ErrorView
import ar.edu.unlam.mobile.scaffolding.ui.core.component.loading.LoadingIndicator
import ar.edu.unlam.mobile.scaffolding.ui.core.state.onError
import ar.edu.unlam.mobile.scaffolding.ui.core.state.onLoading
import ar.edu.unlam.mobile.scaffolding.ui.core.state.onSuccess
import coil.compose.AsyncImage

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    onLogout: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            MainTopAppBar(
                title = stringResource(R.string.profile_title),
                onLogout = onLogout,
            )
        },
    ) { paddingValues ->
        Column(
            modifier =
                Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
        ) {
            state.profileState
                .onLoading {
                    LoadingIndicator()
                }.onSuccess { profile ->
                    if (state.isEditing) {
                        EditProfileContent(
                            profile = profile,
                            onSave = { updatedProfile ->
                                viewModel.saveUserProfile(updatedProfile)
                            },
                            onDismiss = {
                                viewModel.editUserProfile()
                            },
                        )
                    } else {
                        ProfileContent(
                            profile = profile,
                            onEdit = { viewModel.editUserProfile() },
                        )
                    }
                }.onError { message ->
                    ErrorView(
                        message = message,
                        onRetry = { viewModel.retryLoadUserProfile() },
                    )
                }
        }
    }
}

@Composable
fun EditProfileContent(
    profile: Profile,
    onSave: (Profile) -> Unit,
    onDismiss: () -> Unit,
) {
    var avatarUrl by remember { mutableStateOf(profile.avatarUrl) }
    var name by remember { mutableStateOf(profile.name) }
    val email = profile.email

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(30.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = avatarUrl,
            contentDescription = "Avatar",
            modifier = Modifier.size(100.dp),
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Button(
                onClick = { onSave(Profile(name, avatarUrl, email)) },
            ) {
                Text("Guardar")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = onDismiss,
            ) {
                Text("Cancelar")
            }
        }
    }
}

@Composable
fun ProfileContent(
    profile: Profile,
    modifier: Modifier = Modifier,
    onEdit: () -> Unit,
) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .padding(30.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = profile.avatarUrl,
            contentDescription = "Avatar",
            modifier = Modifier.size(100.dp),
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = profile.name,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = profile.email,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onEdit,
        ) {
            Text("Editar Perfil")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileContent(
        profile =
            Profile(
                name = "Jane Doe",
                avatarUrl = "https://ui-avatars.com/api/?name=Usuario+2",
                email = "jdprueba@gmail.com",
            ),
        onEdit = {},
    )
}
