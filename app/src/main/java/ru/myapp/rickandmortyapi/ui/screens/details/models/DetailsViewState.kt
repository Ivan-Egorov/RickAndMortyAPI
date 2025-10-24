package ru.myapp.rickandmortyapi.ui.screens.details.models

interface DetailsViewState {

    data object Loading: DetailsViewState

    //data object Error: DetailsViewState

    data class Display(
        val name: String,
        val status: String,
        val species: String,
        val type: String,
        val gender: String,
        val origin: String,
        val location: String,
        val image: String,
        val episodes: List<String>
    ): DetailsViewState
}