package ru.myapp.rickandmortyapi.domain.models

data class CharacterPreview(
    val id: Int,
    val name: String,
    val status: String,
    val gender: String,
    val species: String,
    val imagePath: String,
)