package com.marvel.characterskmm.data

import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponse (
    val data: CharacterData
)
