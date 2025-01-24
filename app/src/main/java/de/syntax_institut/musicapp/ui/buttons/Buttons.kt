package de.syntax_institut.musicapp.ui.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun FollowButton(
    isFollowing: Boolean,
    followToggle: (Boolean) -> Unit
) {
    Button(
        onClick = { followToggle(!isFollowing) },
        modifier = Modifier
            .shadow(
                elevation = 8.dp,
                shape = ButtonDefaults.elevatedShape,
                clip = false
            )

    ) {
        Text(
            text = if (isFollowing) "Unfollow" else "Follow",
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
fun ShadowButton(
    onClick: () -> Unit,
    text: String,
) {
    Button(
        modifier = Modifier
            .padding(16.dp)
            .shadow(
                elevation = 8.dp,
                shape = ButtonDefaults.elevatedShape,
                ),
        onClick = { onClick() },
    ) {
        Text(text = text)
    }
}
