package ru.myapp.rickandmortyapi.ui.screens.search

import android.util.Log
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
import androidx.compose.runtime.saveable.rememberSaveable
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
import ru.myapp.rickandmortyapi.ui.navigation.NavScreen
import ru.myapp.rickandmortyapi.ui.screens.search.models.SearchAction
import ru.myapp.rickandmortyapi.ui.screens.search.models.SearchEvent
import ru.myapp.rickandmortyapi.ui.screens.search.models.SearchViewState
import ru.myapp.rickandmortyapi.ui.screens.search.views.SearchLoading
import ru.myapp.rickandmortyapi.ui.screens.search.views.SearchViewDisplay

@Composable
fun SearchScreen(
    navController: NavController,
) {
    //val viewModel = remember { SearchViewModel() }
    val viewModel = SearchViewModel()
    val viewState = remember { mutableStateOf<SearchViewState>(SearchViewState.Loading) }
    //val viewAction = remember { mutableStateOf<SearchAction?>(null) }

    val baseUrl = "https://rickandmortyapi.com/api/character"
    val url = rememberSaveable { mutableStateOf(baseUrl) }

    val dispatcher: (SearchEvent) -> Unit = { event ->
        when (event) {
            is SearchEvent.EnterScreen -> {
                viewState.value = SearchViewState.Display(
                    previousPage = event.previousPage,
                    nextPage = event.nextPage,
                    listOfCharacters = event.listOfCharacters
                )
            }
            is SearchEvent.OpenDetails -> {
                navController.navigate(NavScreen.Details(event.id))
            }
            is SearchEvent.ChangePage -> {
                //viewAction.value = SearchAction.UpdateByUrl(event.url)
                url.value = event.url
                viewState.value = SearchViewState.Loading
            }
            is SearchEvent.Search -> {
                url.value = "$baseUrl/?name=${event.name}"
                viewState.value = SearchViewState.Loading
            }
            else -> {}
        }
    }

    when (val state = viewState.value) {
        SearchViewState.Loading -> {
            SearchLoading()
            viewModel.GetByUrl (dispatcher, url.value)
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
                listOfCharacters = state.listOfCharacters,
                dispatcher = dispatcher
            )
        }
    }

    /*when (val action = viewAction.value) {
        SearchAction.ShowFiltersDialog -> {
            FiltersDialog(
                dispatcher = dispatcher,
                onClose = { viewAction.value = null} )
        }
        else -> {}
    }*/

}

