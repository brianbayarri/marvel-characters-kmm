package com.marvel.characterskmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.marvel.characterskmm.Greeting
import android.widget.TextView
import com.marvel.characterskmm.domain.services.CharactersService
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {

    private val charactersService = CharactersService()
    private val mainScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = "Loading..."

        mainScope.launch {
            kotlin.runCatching {
                charactersService.getMarvelCharacters()
            }.onSuccess {
                val character = it[0]
                tv.text = "Nombre: ${character.name} - Descripcion: ${character.description}"
            }.onFailure {
                tv.text = "Error: ${it.localizedMessage}"
            }
        }
    }
}
