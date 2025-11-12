package ru.myapp.rickandmortyapi.ui.screens.search.models

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import ru.myapp.rickandmortyapi.domain.models.CharacterPreview
import ru.myapp.rickandmortyapi.domain.models.Gender
import ru.myapp.rickandmortyapi.domain.models.Status

sealed interface SearchEvent {

    data class EnterScreen(
        val previousPage: String,
        val nextPage: String,
        val listOfCharacters: List<CharacterPreview>
    ): SearchEvent

    data class OpenDetails(val id: Int): SearchEvent

    data class ChangePage(val url: String): SearchEvent

    data class Search(
        val searchField: String,
        val filterStatus: Status,
        val filterSpecies: String,
        val filterType: String,
        val filterGender: Gender,
    ): SearchEvent

    data object Refresh: SearchEvent
}