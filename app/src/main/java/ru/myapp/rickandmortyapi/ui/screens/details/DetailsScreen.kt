package ru.myapp.rickandmortyapi.ui.screens.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import ru.myapp.rickandmortyapi.ui.screens.details.models.DetailsEvent
import ru.myapp.rickandmortyapi.ui.screens.details.models.DetailsViewState
import ru.myapp.rickandmortyapi.ui.screens.details.views.DetailsLoading
import ru.myapp.rickandmortyapi.ui.screens.details.views.DetailsViewDisplay

@Composable
fun DetailsScreen(
    navController: NavController,
    id: Int,
) {
    val viewModel = DetailsViewModel()
    val viewState = remember { mutableStateOf<DetailsViewState>(DetailsViewState.Loading) }

    val dispatcher: (DetailsEvent) -> Unit = { event ->
        when (event) {
            is DetailsEvent.EnterScreen -> {
                viewState.value = DetailsViewState.Display(
                    name = event.name,
                    status = event.status,
                    species = event.species,
                    type = event.type,
                    gender = event.gender,
                    origin = event.origin,
                    location = event.location,
                    image = event.image,
                    episodes = event.episodes,
                )
            }
            DetailsEvent.Close -> { navController.navigateUp() }
            //else -> {}
        }
    }

    when (val state = viewState.value) {
        DetailsViewState.Loading -> {
            DetailsLoading()
            viewModel.GetCharacterDetails(
                id = id,
                dispatcher = dispatcher)

        }
        is DetailsViewState.Display -> {
            DetailsViewDisplay(
                name = state.name,
                status = state.status,
                species = state.species,
                type = state.type,
                gender = state.gender,
                origin = state.origin,
                location = state.location,
                image = state.image,
                episodes = state.episodes,
                dispatcher = dispatcher,
            )
        }
    }

    /*Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("id = $id")
    }*/
}