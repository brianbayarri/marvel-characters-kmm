package com.marvel.characterskmm.domain

import com.marvel.characterskmm.data.Character

class CharacterComparator : Comparator<Character> {

    override fun compare(c1: Character, c2: Character): Int {
        /*if (c1.description.isEmpty() && c2.description.isEmpty()) {
            return c2.id.compareTo(c1.id)
        }
        if (c1.description.isNotEmpty() && c2.description.isNotEmpty()) {
            return c1.id.compareTo(c2.id)
        }
        if (c1.description.isNotEmpty() && c2.description.isEmpty()) {
            return -1
        }*/
        return 1
    }

}