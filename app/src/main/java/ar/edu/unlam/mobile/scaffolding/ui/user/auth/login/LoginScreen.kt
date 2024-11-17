package ar.edu.unlam.mobile.scaffolding.ui.user.auth.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ar.edu.unlam.mobile.scaffolding.ui.core.component.error.ErrorHandler
import ar.edu.unlam.mobile.scaffolding.ui.core.component.loading.LoadingIndicator
import ar.edu.unlam.mobile.scaffolding.ui.core.state.UIState

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigateToRegister: () -> Unit,
    onLoginSuccess: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
        ) {
            LoginForm(
                email = email,
                onEmailChange = setEmail,
                password = password,
                onPasswordChange = setPassword,
                onLoginClick = { viewModel.login(email, password) },
                onRegisterClick = onNavigateToRegister,
                enabled = state.loginState != UIState.Loading,
            )
            when (val loginState = state.loginState) {
                UIState.Loading -> {
                    LoadingIndicator()
                }
                is UIState.Success -> {
                    onLoginSuccess()
                }
                is UIState.Error -> {
                    ErrorHandler(
                        error = loginState.message,
                        onRetry = { viewModel.login(email, password) },
                        snackbarHostState = snackbarHostState,
                        onErrorShown = { viewModel.clearErrorState() }
                    )
                }
                else -> {

                }
            }
        }
    }
}

@Composable
fun LoginForm(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    enabled: Boolean = true
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
            tint = MaterialTheme.colorScheme.primary,
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
        ) {
            Text("Iniciar Sesión")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onRegisterClick,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
        ) {
            Text("Registrarse")
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
        onRegisterClick = {},
    )
}
