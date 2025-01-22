package de.syntax_institut.musicapp.Main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.compose.MusicAppTheme
import de.syntax_institut.musicapp.Screens.FirstScreen
import de.syntax_institut.musicapp.Screens.ProfilScreen
import de.syntax_institut.musicapp.Screens.SongDetailScreen
import de.syntax_institut.musicapp.data.Song
import de.syntax_institut.musicapp.data.songList
import kotlinx.serialization.Serializable

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MusicAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val navController = rememberNavController()
                    var followerCounter by rememberSaveable { mutableStateOf(245) }
                    var isFollowing by rememberSaveable { mutableStateOf(false) }


                    NavHost(
                        navController = navController,
                        startDestination = FirstScreen,
                        modifier = Modifier.padding(innerPadding)
                    )

                    {
                        composable<FirstScreen> {
                            FirstScreen(
                                onNavigateToProfilScreen = {
                                    navController.navigate(ProfilScreen)
                                },

                                onNavigateToDetailScreen = { song: Song ->
                                    navController.navigate(
                                        SongDetailRoute(
                                            artist = song.artist,
                                            title = song.title,
                                            length = song.length,
                                            image = song.image,
                                        )
                                    )
                                },
                                songs = songList,
                            )
                        }

                        composable<SongDetailRoute> {
                            val songDetailRoute = it.toRoute<SongDetailRoute>()
                            Log.d("SongDetailRoute", songDetailRoute.toString())
                            var isMiniScreen by remember { mutableStateOf(false) }

                            SongDetailScreen(
                                song = Song(
                                    artist = songDetailRoute.artist,
                                    title = songDetailRoute.title,
                                    length = songDetailRoute.length,
                                    image = songDetailRoute.image,
                                ),
                                isMiniScreen = isMiniScreen,
                                onToggleScreenMode = { isMiniScreen = it } )
                        }
                         

                        composable<ProfilScreen> {
                            ProfilScreen(
                                onPopUpBackStack = {
                                    navController.popBackStack()
                                },
                                followerCounter = followerCounter,
                                isFollowing = isFollowing,
                                followToggle = { newFollowingState ->
                                    isFollowing = newFollowingState
                                    followerCounter += if (newFollowingState) 1 else -1
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}

@Serializable
object ProfilScreen

@Serializable
object FirstScreen

@Serializable
data class SongDetailRoute(
    val artist: String,
    val title: String,
    val length: Int,
    val image: Int,
)

