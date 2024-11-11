package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.unlam.mobile.scaffolding.domain.model.Tuit
import coil.compose.AsyncImage

@Composable
fun TuitCard(
    tuit: Tuit,
    modifier: Modifier = Modifier,
    likeAction: () -> Unit = {}
) {
    Card(
        modifier = modifier,
    ) {
        Box(modifier = Modifier.padding(8.dp)) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.height(50.dp)
                ) {
                    AsyncImage(
                        model = tuit.avatarUrl,
                        contentDescription = "Android Picture",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(Color.Gray)
                    )

                    Text(
                        tuit.author,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    )
                }

                Text(tuit.message, fontSize = 24.sp)

                LikeButton(
                    color = Color.Black,
                    modifier = Modifier.size(20.dp),
                    onClickAction = likeAction
                )
            }
        }
    }
}

@Preview
@Composable
fun TuitCardPreview() {
    val tuit =
        Tuit(
            id = 1,
            message = "Esto es un tuit de prueba!",
            parentId = 0,
            author = "John Doe",
            avatarUrl = "https://ui-avatars.com/api/?name=John+Doe",
            likes = 5,
            liked = false,
            date = "2024-11-03T10:00:00Z",
            replies = 2,
        )
    TuitCard(tuit)
}
