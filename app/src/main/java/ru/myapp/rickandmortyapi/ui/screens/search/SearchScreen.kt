package ru.myapp.rickandmortyapi.ui.screens.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavController
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
    val viewModel = remember { SearchViewModel() }
    //val viewModel = SearchViewModel()
    val viewState = remember { mutableStateOf<SearchViewState>(SearchViewState.Loading) }

//    val baseUrl = "https://rickandmortyapi.com/api/character"
//    val url = rememberSaveable { mutableStateOf(baseUrl) }
//    val url = rememberSaveable { mutableStateOf("https://rickandmortyapi.com/api/character") }

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
                //url.value = event.url
                viewModel.changeUrl(url = event.url)
                viewState.value = SearchViewState.Loading
            }
            is SearchEvent.Search -> {
                //url.value = "$baseUrl/?name=${event.searchField}"
                viewModel.search(
                    searchField = event.searchField,
                    filterStatus = event.filterStatus,
                    filterSpecies = event.filterSpecies,
                    filterType = event.filterType,
                    filterGender = event.filterGender,
                )

                viewState.value = SearchViewState.Loading
            }
            SearchEvent.Refresh -> { viewState.value = SearchViewState.Loading }
        }
    }

    when (val state = viewState.value) {
        SearchViewState.Loading -> {
            SearchLoading()
            //viewModel.GetByUrl (dispatcher, url.value)
            viewModel.LoadCharacters(dispatcher)
        }
        is SearchViewState.Display -> {

            SearchViewDisplay(
                previousPage = state.previousPage,
                nextPage = state.nextPage,
                listOfCharacters = state.listOfCharacters,
                dispatcher = dispatcher
            )
        }
    }
}

