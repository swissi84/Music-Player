package de.syntax_institut.musicapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.GridOn
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.MusicAppTheme
import de.syntax_institut.musicapp.components.SongGrid
import de.syntax_institut.musicapp.components.SongList
import de.syntax_institut.musicapp.data.songList


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstScreen(
    onNavigateToProfilScreen: () -> Unit,
    modifier: Modifier = Modifier,
    ) {

    var isListView by rememberSaveable  { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Song Gallery") },
                actions = {
                    IconButton(onClick = { isListView = !isListView }) {
                        val icon = if (isListView) Icons.Default.GridOn else Icons.Default.List
                        val description = if (isListView) "Grid View" else "List View"
                        Icon(
                            imageVector = icon,
                            contentDescription = description
                        )
                    }

                    IconButton(
                     modifier = Modifier
                         .padding(horizontal = 16.dp),
                        onClick = { onNavigateToProfilScreen() })
                    {
                        Icon(
                            imageVector = Icons.Default.AccountBox,
                            contentDescription = "ProfilScreen"
                        )
                    }

                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (isListView) {
                SongList(
                    songs = songList,
                    modifier = Modifier.padding(paddingValues)
                )
            } else {
                SongGrid(
                    songs = songList,
                    modifier = Modifier.padding(paddingValues)
                )
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
            )
    }
}
