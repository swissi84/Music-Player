package de.syntax_institut.musicapp.ui.Screens

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
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
import de.syntax_institut.musicapp.data.formattedLength
import de.syntax_institut.musicapp.data.songList
import de.syntax_institut.musicapp.ui.components.SongDetailScreenMini

@Composable
fun SongDetailScreen(
    song: Song,
    modifier: Modifier = Modifier,
    onToggleScreenMode: (Boolean) -> Unit,
    isMiniScreen: Boolean,
    ) {
   
    if (isMiniScreen) {
        SongDetailScreenMini(
            song = song,
            onExpand = { onToggleScreenMode(false) }
        )
    } else {
        SongDetailFullScreen(
            song = song,
            onMinimize = { onToggleScreenMode(true) }
        )
    }
}

@Composable
fun SongDetailFullScreen(
    song: Song,
    onMinimize: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            IconButton(onClick = onMinimize) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Minimize"
                )
            }

            Box(
                modifier = Modifier
                    .size(250.dp)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Image(
                    painter = painterResource(id = song.image),
                    contentDescription = "Titel Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop,
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(song.artist, style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(song.title, style = MaterialTheme.typography.titleMedium)


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Slider(
                    value = 0.0f,
                    onValueChange = { },
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            Text(song.formattedLength)

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 64.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.SkipPrevious,
                        contentDescription = "Previous"
                    )
                }
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Play"
                    )
                }
                IconButton(onClick = {  }) {
                    Icon(
                        imageVector = Icons.Default.SkipNext,
                        contentDescription = "Next"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SongDetailScreenPreview() {
    MusicAppTheme {
        SongDetailScreen(
            songList.random(),
            onToggleScreenMode = { },
            isMiniScreen = false,
        )
    }
}