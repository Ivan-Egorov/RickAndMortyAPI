package ru.myapp.rickandmortyapi.ui.screens.search.models

import ru.myapp.rickandmortyapi.domain.models.CharacterPreview

sealed interface SearchViewState {

    data object Loading: SearchViewState

    //data object Error: SearchViewState

    data class Display(
        val previousPage: String,
        val nextPage: String,
        val listOfCharacters: List<CharacterPreview>
    ): SearchViewState
}