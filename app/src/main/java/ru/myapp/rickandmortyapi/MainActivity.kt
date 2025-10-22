package ru.myapp.rickandmortyapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import ru.myapp.rickandmortyapi.ui.theme.RickAndMortyAPITheme
import ru.myapp.rickandmortyapi.ui.theme.components.JetCard

import coil3.compose.AsyncImage
import ru.myapp.rickandmortyapi.ui.screens.search.views.SearchViewDisplay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

//            JetAsyncImage()

            /*Box(
                modifier = Modifier
                    .size(300.dp)
                    //.background(color = Color.Red.copy(0.2f))
            ) {
                AsyncImage(
                    model = "https://ucarecdn.com/cee54742-2515-44f9-bdca-bec24f28e083/",
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth
                )
            }*/

            RickAndMortyAPITheme {
                SearchViewDisplay()

                /*JetCard(
                    modifier = Modifier.padding(20.dp),
                    name = "Trandor",
                    status = "Alive",
                    gender = "Male",
                    race = "Alien",
                    imagePath = "file:///android_asset/img.png"
                ) {}*/
            }
        }
    }
}


@Composable
fun JetAsyncImage() {

    Box(
        Modifier
            .size(256.dp, 96.dp)
    ) {
        AsyncImage(
            model = "https://ucarecdn.com/cee54742-2515-44f9-bdca-bec24f28e083/",
            contentDescription = "",
            contentScale = ContentScale.FillHeight
        )
    }
}
