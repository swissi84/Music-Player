package de.syntax_institut.musicapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.magnifier
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.syntax_institut.musicapp.components.SongGridItem
import de.syntax_institut.musicapp.data.Song
import de.syntax_institut.musicapp.data.songList
import de.syntax_institut.musicapp.ui.theme.MusicAppTheme
import de.syntax_institut.musicapp.SongGrid as SongGrid

@Composable
fun SongGrid(songs: List<Song>, modifier: Modifier) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(6.dp),
        contentPadding = PaddingValues(4.dp),
    )
    {
        items(songs) { song ->
            SongGridItem(song)

        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSongGrid() {
    MusicAppTheme {
        SongGrid(
            songs = songList,
            modifier = Modifier.padding(16.dp),
        )
    }

}