package com.marvel.characterskmm.android.ui.error

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.marvel.characterskmm.android.databinding.ActivityErrorBinding
import com.marvel.characterskmm.android.ui.characters.CharactersActivity


class ErrorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityErrorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRetry.setOnClickListener {
            val charactersActivityIntent = Intent(this, CharactersActivity::class.java)
            startActivity(charactersActivityIntent)
        }
    }
}