package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffolding.domain.model.Tuit

@Composable
fun TuitFeed(tuits: List<Tuit>) {
    LazyColumn {
        items(tuits) { tuit ->
            TuitCard(tuit, modifier = Modifier.padding(1.dp))
        }
    }
}

@Preview
@Composable
fun TuitFeedPreview() {
    val tuits =
        listOf(
            Tuit(
                id = 1,
                message = "Esto es un tuit de prueba!",
                parentId = 0,
                author = "John Doe",
                avatarUrl = "https://ui-avatars.com/api/?name=John+Doe",
                likes = 5,
                liked = false,
                date = "2024-11-03T10:00:00Z",
                replies = 2
            ),
            Tuit(
                id = 2,
                message = "Otro tuit de prueba!",
                parentId = 0,
                author = "Jane Doe",
                avatarUrl = "https://ui-avatars.com/api/?name=Jane+Doe",
                likes = 3,
                liked = true,
                date = "2024-11-03T11:30:00Z",
                replies = 0
            ),
        )
    TuitFeed(tuits = tuits)
}
