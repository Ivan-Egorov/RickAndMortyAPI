package ru.myapp.rickandmortyapi.ui.screens.details.models

sealed interface DetailsEvent {

    data class EnterScreen(
        val name: String,
        val status: String,
        val species: String,
        val type: String,
        val gender: String,
        val origin: String,
        val location: String,
        val image: String,
        val episodes: List<String>
    ): DetailsEvent

    data object Close: DetailsEvent
}