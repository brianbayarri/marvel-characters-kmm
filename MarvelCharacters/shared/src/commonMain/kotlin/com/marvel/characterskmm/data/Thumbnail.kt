package com.marvel.characterskmm.data

import kotlinx.serialization.Serializable

@Serializable
data class Thumbnail(
    val path: String,
    val extension: String
) {
    fun toUrl() = "$path.$extension"
}
