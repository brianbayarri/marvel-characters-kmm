package com.marvel.characterskmm.data

import kotlinx.serialization.Serializable

@Serializable
data class CharacterResult(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
)
