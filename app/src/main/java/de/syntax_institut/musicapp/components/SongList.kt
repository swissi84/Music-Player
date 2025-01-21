package de.syntax_institut.musicapp.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.syntax_institut.musicapp.data.Song
import de.syntax_institut.musicapp.data.songList
import com.example.compose.MusicAppTheme


@Composable
fun SongList(songs: List<Song>, modifier: Any) {
    LazyColumn {
        items(songs) { song ->
            SongListItem(song)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSongList() {
    MusicAppTheme {
val paddingValues = 16.dp
        SongList(
    songs = songList,
    modifier = Modifier.padding(paddingValues)
)
    }
}