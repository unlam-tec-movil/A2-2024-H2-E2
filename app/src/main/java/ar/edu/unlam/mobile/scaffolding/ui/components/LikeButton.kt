package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LikeButton(
    isLiked: Boolean,
    color: Color = Color.Red,
    modifier: Modifier = Modifier,
    onClickAction: () -> Unit = {},
) {
    IconToggleButton(
        checked = isLiked,
        onCheckedChange = {
            onClickAction()
        },
        modifier = modifier,
    ) {
        Icon(
            imageVector =
            if (isLiked) {
                Icons.Filled.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = "Corazon",
            tint = color,
        )
    }
}

@Preview
@Composable
fun LikeButtonPreview() {
    LikeButton(true)
}
