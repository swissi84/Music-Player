package de.syntax_institut.musicapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
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

