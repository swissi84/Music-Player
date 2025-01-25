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
import android.content.Context
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.compose.ui.viewinterop.AndroidView

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.MusicAppTheme
import de.syntax_institut.musicapp.data.Song

import de.syntax_institut.musicapp.data.songList
import de.syntax_institut.musicapp.ui.components.SongDetailScreenMini
import kotlinx.coroutines.delay

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
            onExpand = { onToggleScreenMode(false) },
            play = { }
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
    val context = LocalContext.current
    var isPlaying by remember { mutableStateOf(false) }
    var player: ExoPlayer? by remember { mutableStateOf(null) }
    var playbackProgress by remember { mutableStateOf(0f) }
    var currentPositionFormatted by remember { mutableStateOf("0:00") }

    val sliderColors = SliderDefaults.colors(
        thumbColor = Color.Yellow,
        activeTrackColor = Color.Yellow,
        activeTickColor = Color.Transparent,
        inactiveTrackColor = Color.Gray,
        inactiveTickColor = Color.Transparent,
    )

    LaunchedEffect(Unit) {
        player = ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri(song.localUri)
            setMediaItem(mediaItem)
            prepare()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = song.image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.3f),
            contentScale = ContentScale.Crop,
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background.copy(alpha = 0.7f)),
        )

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
                    value = playbackProgress,
                    onValueChange = {
                        playbackProgress = it
                        player?.seekTo((it * (player?.duration ?: 0)).toLong())
                    },
                    colors = sliderColors

                )
            }

            Spacer(modifier = Modifier.height(10.dp))


            Text(currentPositionFormatted, )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 64.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = {  }) {
                    Icon(
                        imageVector = Icons.Default.SkipPrevious,
                        contentDescription = "Previous",
                        tint = Color.Gray
                    )
                }
                IconButton(onClick = {
                    player?.let {
                        if (it.isPlaying) {
                            it.pause()
                        } else {
                            it.play()
                        }
                        isPlaying = it.isPlaying
                    }
                }) {
                    Icon(
                        imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                        contentDescription = "Play/Pause"
                    )
                }
                IconButton(onClick = {  }) {
                    Icon(
                        imageVector = Icons.Default.SkipNext,
                        contentDescription = "Next",
                        tint = Color.Gray

                    )
                }
            }
        }
    }

    LaunchedEffect(isPlaying, player?.currentPosition, player?.duration) {
        while (isPlaying) {
            delay(500)

            val currentPosition = player?.currentPosition?.toFloat() ?: 0f
            val duration = player?.duration?.toFloat() ?: 1f

            playbackProgress = if (duration > 0) currentPosition / duration else 0f
            currentPositionFormatted = formatDuration(currentPosition.toLong())

        }
    }


    DisposableEffect(Unit){
        onDispose {
            player?.release()
            player = null
        }
    }
}

val Song.localUri: String
    get() {
        return "android.resource://com.example.myapplication/${this.audio}"
    }

private fun formatDuration(durationMs: Long): String {
    val totalSeconds = durationMs / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return String.format("%d:%02d", minutes, seconds)
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