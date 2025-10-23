package ru.myapp.rickandmortyapi.ui.screens.search.models

import ru.myapp.rickandmortyapi.domain.models.CharacterPreview

sealed interface SearchEvent {

    data class EnterScreen(
        val previousPage: String,
        val nextPage: String,
        val listOfCharacters: List<CharacterPreview>
    ): SearchEvent

    data object NextPage: SearchEvent

    data object PreviousPage: SearchEvent

    data class OpenDetails(val id: Int): SearchEvent

    data class Search(val query: String): SearchEvent
}