package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ar.edu.unlam.mobile.scaffolding.R

@Composable
fun MainTopAppBar(
    title: String,
    onLogout: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = title) },
        actions = {
            IconButton(onClick = onLogout) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = stringResource(R.string.logout),
                )
            }
        },
    )
}
