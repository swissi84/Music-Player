package de.syntax_institut.musicapp.ui.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.MusicAppTheme
import de.syntax_institut.musicapp.ui.buttons.FollowButton
import de.syntax_institut.musicapp.R
import de.syntax_institut.musicapp.ui.buttons.ShadowButton

@Composable
fun ProfilScreen(
    modifier: Modifier = Modifier,
    onPopUpBackStack: () -> Unit,
    followerCounter: Int,
    isFollowing: Boolean,
    followToggle: (Boolean) -> Unit,
    
) {

    Column(
        modifier = modifier
            .fillMaxSize(),

        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

          ShadowButton(
              onClick = onPopUpBackStack,
              text = "Back"
          )

        }

        Image(
            painter = painterResource(id = R.drawable.user_pic),
            contentDescription = "DJ Image",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 9f),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            StatColumn(value = "$followerCounter", label = "Follower")
            StatColumn(value = "180", label = "Folgt")
            StatColumn(value = "52", label = "Playlists")
        }

        Spacer(modifier = Modifier.height(16.dp))


        FollowButton(
            isFollowing = isFollowing,
            followToggle = followToggle
        )

        Spacer(modifier = Modifier.height(16.dp))


        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Lieblingsgenres",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                GenreChip(label = "Rock")
                GenreChip(label = "Pop")
                GenreChip(label = "Hip-Hop")
            }
        }
    }
}

@Composable
fun StatColumn(value: String, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
fun GenreChip(label: String) {
    Card(
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier.padding(4.dp)
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDJProfileScreen() {
    MusicAppTheme {
        var followerCounter by remember { mutableStateOf(245) }
        var isFollowing by remember { mutableStateOf(false) }

        ProfilScreen(
            onPopUpBackStack = {},
            followerCounter = followerCounter,
            isFollowing = isFollowing,
            followToggle = { newFollowingState ->
                isFollowing = newFollowingState
                followerCounter += if (newFollowingState) 1 else -1
            },
        )
    }
}




