package ru.myapp.rickandmortyapi.ui.screens.details.models

interface DetailsViewState {

    data object Loading: DetailsViewState

    //data object Error: DetailsViewState

    data class Display(val id: Int): DetailsViewState
}