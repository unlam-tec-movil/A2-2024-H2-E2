package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.unlam.mobile.scaffolding.domain.model.FavoriteUser
import coil.compose.AsyncImage

@Composable
fun FavoriteUserCard(
    favoriteUser: FavoriteUser,
    onDeleteUser: (FavoriteUser) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.padding(8.dp),
    ) {
        Box(
            modifier = Modifier.padding(15.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(8.dp),
            ) {
                AsyncImage(
                    model = favoriteUser.avatarUrl,
                    contentDescription = "User avatar",
                    modifier =
                        Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(Color.Gray),
                )

                Text(
                    favoriteUser.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier =
                        Modifier
                            .padding(start = 10.dp)
                            .weight(15f)
                            .fillMaxWidth(),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )

                Spacer(modifier = Modifier.weight(1f))

                IconButton(onClick = { onDeleteUser(favoriteUser) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete user",
                        tint = Color.Red,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteUserCardPreview() {
    val favUser =
        FavoriteUser(
            name = "Usuario Prueba",
            avatarUrl = "https://static-00.iconduck.com/assets.00/profile-default-icon-512x511-v4sw4m29.png",
        )
    FavoriteUserCard(
        favoriteUser = favUser,
        onDeleteUser = {},
    )
}
