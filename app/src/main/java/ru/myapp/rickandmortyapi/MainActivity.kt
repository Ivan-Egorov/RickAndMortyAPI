package ru.myapp.rickandmortyapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ru.myapp.rickandmortyapi.ui.theme.RickAndMortyAPITheme
import ru.myapp.rickandmortyapi.ui.navigation.NavHostScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            RickAndMortyAPITheme {
                NavHostScreen()
            }
        }
    }
}
