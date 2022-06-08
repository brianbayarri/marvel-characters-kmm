package com.marvel.characterskmm.domain.extensions

import com.marvel.characterskmm.data.Character
import com.marvel.characterskmm.data.CharactersResponse

fun CharactersResponse.toModel(): List<Character> {
    return this.data.results.map {
        Character(
            id = it.id,
            name = it.name,
            description = it.description,
            thumbnailUrl = it.thumbnail.toUrl()
        )
    }
}