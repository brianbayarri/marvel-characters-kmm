package com.marvel.characterskmm.data

import kotlinx.serialization.Serializable

@Serializable
data class CharacterData (
    val results: List<CharacterResult>
)