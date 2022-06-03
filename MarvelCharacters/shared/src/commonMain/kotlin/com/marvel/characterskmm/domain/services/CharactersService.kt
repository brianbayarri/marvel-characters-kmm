package com.marvel.characterskmm.domain.services

import com.marvel.characterskmm.data.Character
import com.marvel.characterskmm.domain.CharacterComparator
import com.marvel.characterskmm.domain.PRIVATE_KEY
import com.marvel.characterskmm.domain.PUBLIC_KEY
import com.marvel.characterskmm.domain.repositories.CharactersRepository
import io.ktor.util.date.*
import io.ktor.utils.io.core.*

class CharactersService {

    private val characterComparator = CharacterComparator()
    private val charactersRepository = CharactersRepository()

    suspend fun getMarvelCharacters() : Character {
        val timestamp = getTimeMillis()
        val characters = charactersRepository.get(
            timestamp,
            md5(timestamp.toString() + PRIVATE_KEY + PUBLIC_KEY)
        )

        return characters
        //return sort(characters)
    }

    private fun md5(string: String): String {
        return ""
        /*
        val MD5 = "MD5"
        try {
            // Create MD5 Hash
            string.toByteArray().md5()
            val digest = MessageDigest
                .getInstance(MD5)
            digest.update(string.toByteArray())
            val messageDigest = digest.digest()

            // Create Hex String
            val hexString = StringBuilder()
            for (aMessageDigest in messageDigest) {
                var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                while (h.length < 2) h = "0$h"
                hexString.append(h)
            }
            return hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""*/
    }

    private fun sort(characters: List<Character>): List<Character> {
        return characters.sortedWith(characterComparator)
    }

}