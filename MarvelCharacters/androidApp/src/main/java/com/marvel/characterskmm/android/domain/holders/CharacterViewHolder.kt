package com.marvel.characterskmm.android.domain.holders

import androidx.recyclerview.widget.RecyclerView
import com.marvel.characterskmm.android.databinding.ListItemCharacterBinding
import com.marvel.characterskmm.data.Character
import com.squareup.picasso.Picasso

class CharacterViewHolder(private val binding: ListItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(character: Character) {
        binding.name.text = character.name
        binding.description.text = character.description
        if (character.thumbnailUrl.isNotEmpty()) {
            Picasso.get()
                .load(character.thumbnailUrl)
                .into(binding.image)
        } else {
            binding.image.setImageURI(null)
        }
    }

}