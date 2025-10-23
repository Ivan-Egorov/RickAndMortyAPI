package ru.myapp.rickandmortyapi.ui.screens.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.myapp.rickandmortyapi.ui.screens.search.models.SearchEvent
import ru.myapp.rickandmortyapi.ui.screens.search.models.SearchViewState
import ru.myapp.rickandmortyapi.ui.screens.search.views.SearchLoading
import ru.myapp.rickandmortyapi.ui.screens.search.views.SearchViewDisplay

@Composable
fun SearchViewScreen(
    navController: NavController,
) {
    val viewModel = remember { SearchViewModel() }
    val viewState = remember { mutableStateOf<SearchViewState>(SearchViewState.Loading) }

    val dispatcher: (SearchEvent) -> Unit = { event ->
        when (event) {
            is SearchEvent.EnterScreen -> {
                viewState.value = SearchViewState.Display(
                    previousPage = event.previousPage,
                    nextPage = event.nextPage,
                    listOfCharacters = event.listOfCharacters
                )
            }
            else -> {}
        }
    }

    when (val state = viewState.value) {
        SearchViewState.Loading -> {
            SearchLoading()
            viewModel.GetCharacters (dispatcher)
        }
        is SearchViewState.Display -> {

            /*Column(modifier = Modifier.padding(20.dp)
            ) {
                Text(text = state.previousPage)
                Text(text = state.nextPage)
                Text(text = state.listOfCharacters.toString())
            }*/

            SearchViewDisplay(
                previousPage = state.previousPage,
                nextPage = state.nextPage,
                listOfCharacters = state.listOfCharacters
            )
        }
    }

    //SearchViewDisplay()
}

