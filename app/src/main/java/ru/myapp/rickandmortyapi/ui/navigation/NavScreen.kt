package ru.myapp.rickandmortyapi.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface NavScreen {
    @Serializable
    data object Search: NavScreen
    @Serializable
    data class Details(val id: Int): NavScreen
}