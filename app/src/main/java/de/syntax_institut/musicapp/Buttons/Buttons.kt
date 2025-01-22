package de.syntax_institut.musicapp.Buttons

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight




@Composable
fun FollowButton(
    isFollowing: Boolean,
    followToggle: (Boolean) -> Unit
) {
    Button(
        onClick = { followToggle(!isFollowing)}
    ) {
        Text(
            text = if (isFollowing) "Unfollow" else "Follow",
            fontWeight = FontWeight.Bold
        )
    }
}

