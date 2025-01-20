package de.syntax_institut.musicapp.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.syntax_institut.musicapp.data.Song
import de.syntax_institut.musicapp.data.songList
import de.syntax_institut.musicapp.ui.theme.MusicAppTheme

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