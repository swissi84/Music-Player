package de.syntax_institut.musicapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.MusicAppTheme
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
                    ) {
                        composable<FirstScreen> {
                           FirstScreen(
                               onNavigateToProfilScreen = {
                                   navController.navigate(ProfilScreen)
                               },

                           )
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

