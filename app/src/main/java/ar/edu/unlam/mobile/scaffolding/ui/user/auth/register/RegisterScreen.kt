package ar.edu.unlam.mobile.scaffolding.ui.user.auth.register

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
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
import ar.edu.unlam.mobile.scaffolding.ui.core.component.error.ErrorView
import ar.edu.unlam.mobile.scaffolding.ui.core.component.loading.LoadingIndicator
import ar.edu.unlam.mobile.scaffolding.ui.core.state.UIState

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    onNavigateBackErrorView: () -> Unit,
    onRegisterSuccess: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val (name, setName) = remember { mutableStateOf("") }
    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }

    when (val registerState = state.registerState) {
        UIState.Loading -> LoadingIndicator()
        is UIState.Success -> onRegisterSuccess()
        is UIState.Error ->
            ErrorView(
                message = registerState.message,
                onRetry = { viewModel.register(name, email, password) },
                isRetrying = registerState is UIState.Loading,
                onBack = onNavigateBackErrorView,
            )
        else -> RegisterForm(
            name = name,
            onNameChange = setName,
            email = email,
            onEmailChange = setEmail,
            password = password,
            onPasswordChange = setPassword,
            onRegisterClick = { viewModel.register(name, email, password) },
            onBackClick = onNavigateBack,
        )
    }
}

@Composable
fun RegisterForm(
    name: String,
    onNameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onRegisterClick: () -> Unit,
    onBackClick: () -> Unit,
) {
    Column(
        modifier = Modifier
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
            value = name,
            onValueChange = onNameChange,
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(8.dp))
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
            label = { Text("ContraseÃ±a") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onRegisterClick,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Registrarse")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
            ),
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back Icon",
                modifier = Modifier.size(24.dp),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Volver")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterForm(
        name = "Jane Doe",
        onNameChange = {},
        email = "jdoe@example.com",
        onEmailChange = {},
        password = "zzz123",
        onPasswordChange = {},
        onRegisterClick = {},
        onBackClick = {},
    )
}
