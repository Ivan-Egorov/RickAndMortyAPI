package ru.myapp.rickandmortyapi.ui.screens.details.models

sealed interface DetailsEvent {

    data object Close: DetailsEvent
}