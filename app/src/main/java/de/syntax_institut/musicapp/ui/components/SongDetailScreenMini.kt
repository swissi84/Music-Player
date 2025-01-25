package de.syntax_institut.musicapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.MusicAppTheme
import de.syntax_institut.musicapp.data.Song
import de.syntax_institut.musicapp.data.songList

@Composable
fun SongDetailScreenMini(
    song: Song,
    modifier: Modifier = Modifier,
    onExpand: () -> Unit,
    play: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceContainerLowest),
        contentAlignment = Alignment.BottomCenter

    ) {
        Column(
            modifier = modifier
                .background(MaterialTheme.colorScheme.surface),
            horizontalAlignment = Alignment.CenterHorizontally
            ) {

            IconButton(onClick = onExpand) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "Minimize"
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = song.image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(70.dp))

                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.Center

                ) {
                    Text(song.title, style = MaterialTheme.typography.bodyLarge)
                    Text(song.artist, style = MaterialTheme.typography.bodyMedium)
                }
                IconButton(onClick = { play() }) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Play"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SongDetailScreenMiniPreview() {
    MusicAppTheme {
        SongDetailScreenMini(
            songList.random(),
            onExpand = {},
            play = {},
        )
    }
}