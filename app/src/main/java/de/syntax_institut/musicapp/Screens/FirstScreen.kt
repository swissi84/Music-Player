package de.syntax_institut.musicapp.Screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.GridOn
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.MusicAppTheme
import com.example.compose.backgroundLight
import de.syntax_institut.musicapp.components.SongGridItem
import de.syntax_institut.musicapp.components.SongListItem
import de.syntax_institut.musicapp.data.Song
import de.syntax_institut.musicapp.data.songList


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstScreen(
    songs: List<Song>,
    onNavigateToProfilScreen: () -> Unit,
    onNavigateToDetailScreen: (Song) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isListView by rememberSaveable { mutableStateOf(true) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(

        ) {
Box {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween, Alignment.CenterVertically


    ) {
        Text("Song Gallery", style = MaterialTheme.typography.titleLarge)

        IconButton(onClick = { isListView = !isListView }) {
            val icon = if (isListView) Icons.Default.GridOn else Icons.Default.List
            val description = if (isListView) "Grid View" else "List View"
            Icon(
                imageVector = icon,
                contentDescription = description
            )
        }
    }
}
            if (isListView) {
                LazyColumn {
                    items(songs) { song ->
                        SongListItem(
                            song = song,
                            onClick = { onNavigateToDetailScreen(song) }
                        )
                    }
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(6.dp),
                    contentPadding = PaddingValues(4.dp),
                )
                {
                    items(songs) { song ->
                        SongGridItem(
                            song = song,
                            onClick = { onNavigateToDetailScreen(song) }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MusicAppPreview() {
    MusicAppTheme {
        FirstScreen(
            onNavigateToProfilScreen = {},
            songs = songList,
            onNavigateToDetailScreen = {}
        )
    }
}

