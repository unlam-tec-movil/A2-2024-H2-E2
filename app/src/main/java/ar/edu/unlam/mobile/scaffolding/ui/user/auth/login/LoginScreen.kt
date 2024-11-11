package ar.edu.unlam.mobile.scaffolding.ui.user.auth.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import ar.edu.unlam.mobile.scaffolding.ui.core.component.error.ErrorView
import ar.edu.unlam.mobile.scaffolding.ui.core.component.loading.LoadingIndicator
import ar.edu.unlam.mobile.scaffolding.ui.core.state.UIState
import ar.edu.unlam.mobile.scaffolding.ui.core.state.onError
import ar.edu.unlam.mobile.scaffolding.ui.core.state.onLoading
import ar.edu.unlam.mobile.scaffolding.ui.core.state.onSuccess

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    state.loginState
        .onLoading {
            LoadingIndicator()
        }.onSuccess {
            navController.navigate("home")
        }.onError { message ->
            ErrorView(
                message = message,
                onRetry = {
                    viewModel.login()
                },
                isRetrying = state.loginState is UIState.Loading,
            )
        }

    LoginForm(
        email = viewModel.getEmail(),
        onEmailChange = viewModel::onEmailChange,
        password = viewModel.getPassword(),
        onPasswordChange = viewModel::onPasswordChange,
        onLoginClick = viewModel::login,
    )
}

@Composable
fun LoginForm(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Person Icon",
            modifier = Modifier.size(100.dp),
            colorResource(
                id = ar.edu.unlam.mobile.scaffolding.R.color.purple_500,
            ),
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Iniciar Sesión")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginForm(
        email = "jdoe@example.com",
        onEmailChange = {},
        password = "zzz123",
        onPasswordChange = {},
        onLoginClick = {},
    )
}
