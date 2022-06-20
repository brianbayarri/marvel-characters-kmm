package com.marvel.characterskmm.android.domain.services

import android.content.Context
import android.util.Log
import com.characters.cache.db.AppDatabase
import com.marvel.characterskmm.DatabaseDriverFactory
import com.marvel.characterskmm.data.Character

class CacheService(context: Context) {

    var database: AppDatabase = AppDatabase(DatabaseDriverFactory(context).createDriver())

    fun populate(characters: List<Character>) {

        database.charactersQueries.transaction {
            afterCommit { Log.d("Cache population", "${characters.size} characters were inserted.") }

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