package ar.edu.unlam.mobile.scaffolding.ui.components

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.unlam.mobile.scaffolding.domain.model.DraftTuit
import java.util.Date

@Composable
fun TuitDraftCard(
    tuitDraft: DraftTuit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
    ) {
        Box(modifier = Modifier.padding(8.dp)) {
            Column {
                Row {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                    ) {
                        Text(formatDate(tuitDraft.lastModified), fontSize = 16.sp)
                    }
                }
                Row {
                    Text(tuitDraft.message, fontSize = 24.sp)
                }
            }
        }
    }
}

private fun formatDate(timestamp: Long): String {
    val locale = java.util.Locale("es", "AR")
    return SimpleDateFormat("dd/MM/yyyy HH:mm", locale)
        .format(Date(timestamp))
}

@Preview
@Composable
fun TuitDraftCardPreview() {
    val tuitDraft =
        DraftTuit(
            message = "Esto es un tuit borrador de prueba!",
            lastModified = System.currentTimeMillis(),
        )
    TuitDraftCard(tuitDraft)
}
