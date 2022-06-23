package com.marvel.characterskmm.domain.services

import com.marvel.characterskmm.BuildKonfig
import com.marvel.characterskmm.data.Character
import com.marvel.characterskmm.domain.CharacterComparator
import com.marvel.characterskmm.domain.extensions.toModel
import com.marvel.characterskmm.domain.repositories.CharactersRepository
import com.marvel.characterskmm.utils.HashGenerator
import io.ktor.util.date.*

class CharactersService {

    private val characterComparator = CharacterComparator()
    private val charactersRepository = CharactersRepository()

    suspend fun getMarvelCharacters() : List<Character> {
        val timestamp = getTimeMillis()

        val charactersResponse = charactersRepository.get(
            timestamp,
            HashGenerator.MD5(timestamp.toString() + BuildKonfig.MARVEL_PRIVATE_KEY + BuildKonfig.MARVEL_PUBLIC_KEY)
        )

        val characters = charactersResponse.toModel()

        return sort(characters)
    }

    private fun sort(characters: List<Character>): List<Character> {
        return characters.sortedWith(characterComparator)
    }

}