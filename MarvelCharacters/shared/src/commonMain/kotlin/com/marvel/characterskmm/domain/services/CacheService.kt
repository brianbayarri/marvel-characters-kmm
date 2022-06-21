package com.marvel.characterskmm.domain.services

import com.characters.cache.db.AppDatabase
import com.marvel.characterskmm.data.Character
import io.github.aakira.napier.Napier

class CacheService (private val database: AppDatabase) {

    fun populate(characters: List<Character>) {
        database.charactersQueries.transaction {
            afterCommit { Napier.d(tag = "Cache", message = "${characters.size} characters were inserted.") }

            characters.forEach{ character ->
                database.charactersQueries.insertCharacter(
                    id = character.id,
                    name = character.name,
                    description = character.description,
                    thumbnailUrl = character.thumbnailUrl
                )
            }
        }
    }

    fun get() : List<Character> {
        val characters : List<Character> = database.charactersQueries.selectCharacters{
                id, name, description, thumbnailUrl ->  Character(id, name, description, thumbnailUrl)
        }.executeAsList()

        return characters
    }
}