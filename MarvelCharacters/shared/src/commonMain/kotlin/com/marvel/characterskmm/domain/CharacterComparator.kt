package com.marvel.characterskmm.domain

import com.marvel.characterskmm.data.Character

class CharacterComparator : Comparator<Character> {

    override fun compare(character1: Character, character2: Character): Int {
        if (character1.description.isEmpty() && character2.description.isEmpty()) {
            return character2.id.compareTo(character1.id)
        }
        if (character1.description.isNotEmpty() && character2.description.isNotEmpty()) {
            return character1.id.compareTo(character2.id)
        }
        if (character1.description.isNotEmpty() && character2.description.isEmpty()) {
            return -1
        }
        return 1
    }

}