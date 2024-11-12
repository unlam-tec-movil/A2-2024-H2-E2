package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffolding.domain.model.DraftTuit

@Composable
fun TuitDraftFeed(drafts: List<DraftTuit>) {
    LazyColumn {
        items(drafts) { draft ->
            TuitDraftCard(draft, modifier = Modifier.padding(1.dp))
        }
    }
}

@Preview
@Composable
fun TuitDraftFeedPreview() {
    val drafts =
        listOf(
            DraftTuit(
                message = "Esto es un tuit borrador de prueba!",
                lastModified = System.currentTimeMillis(),
            ),
            DraftTuit(
                message = "Esto es otro tuit borrador de prueba!",
                lastModified = System.currentTimeMillis(),
            ),
        )
    TuitDraftFeed(drafts = drafts)
}
