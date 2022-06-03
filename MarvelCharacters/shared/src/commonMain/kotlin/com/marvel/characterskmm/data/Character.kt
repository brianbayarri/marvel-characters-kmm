package com.marvel.characterskmm.data

import kotlinx.serialization.Serializable

/*data class Character(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnailUrl: String
)*/

@Serializable
data class Character(
    val estado: String,
    val temperatura: String,
    val hora: String
)
