package de.syntax_institut.musicapp.Main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.compose.MusicAppTheme
import de.syntax_institut.musicapp.ui.Screens.FirstScreen
import de.syntax_institut.musicapp.ui.Screens.ProfilScreen
import de.syntax_institut.musicapp.ui.Screens.SearchScreen
import de.syntax_institut.musicapp.ui.Screens.SongDetailScreen
import de.syntax_institut.musicapp.data.Song
import de.syntax_institut.musicapp.data.songList
import kotlinx.serialization.Serializable

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val currentBackStackEntry by navController.currentBackStackEntryAsState()
            var followerCounter by rememberSaveable { mutableStateOf(245) }
            var isFollowing by rememberSaveable { mutableStateOf(false) }

            MusicAppTheme {
                Scaffold(modifier = Modifier
                    .fillMaxSize(),

                    bottomBar = {
                        NavigationBar {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination

                            NavItem.entries.forEachIndexed { index, item ->
                                NavigationBarItem(
                                    selected = currentBackStackEntry?.destination?.hasRoute(item.route::class) ?: false,
                                    onClick = {
                                        navController.navigate(item.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                    icon = {
                                        Icon(imageVector = item.icon, contentDescription = item.label)
                                    },
                                    label = {
                                        Text(item.label)
                                    },

                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = FirstScreen,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
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
                                            audio = song.audio,
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
                                    audio = songDetailRoute.audio,
                                ),
                                isMiniScreen = isMiniScreen,
                                onToggleScreenMode = { isMiniScreen = it } )
                        }

                        composable<SearchScreen> {
                            SearchScreen()
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
object FirstScreen

@Serializable
object SearchScreen

@Serializable
object ProfilScreen



@Serializable
data class SongDetailRoute(
    val artist: String,
    val title: String,
    val length: Int,
    val image: Int,
    val audio: Int,
)

enum class NavItem(
    val route: Any,
    val label: String,
    val icon: ImageVector,
) {
    First(FirstScreen,"Home", Icons.Filled.Home),
    Second(SearchScreen,"Search", Icons.Filled.Search),
    Third(ProfilScreen,"Profil", Icons.Filled.AccountCircle),
}