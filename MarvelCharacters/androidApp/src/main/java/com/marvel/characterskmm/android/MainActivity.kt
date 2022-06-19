package com.marvel.characterskmm.android

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.marvel.characterskmm.android.databinding.ActivityMainBinding
import com.marvel.characterskmm.android.domain.adapters.CharactersAdapter
import com.marvel.characterskmm.android.domain.utils.VerticalSpaceItemDecoration
import com.marvel.characterskmm.android.ui.characters.CharactersViewModel
import com.marvel.characterskmm.android.ui.characters.CharactersViewModelFactory
import com.marvel.characterskmm.android.ui.characters.ScreenState
import kotlinx.coroutines.launch
import com.marvel.characterskmm.data.Character

class MainActivity : AppCompatActivity() {

    private lateinit var charactersAdapter: CharactersAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        // Setup del listado
        charactersAdapter = CharactersAdapter()
        val verticalLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        with(binding.charactersList) {
            this.adapter = charactersAdapter
            this.layoutManager = verticalLayoutManager
            this.addItemDecoration(VerticalSpaceItemDecoration(16))
        }

        // Listen to marvel api results
        val viewModel = ViewModelProvider(this, CharactersViewModelFactory())[CharactersViewModel::class.java]
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.screenState.collect {
                    when (it) {
                        ScreenState.Loading -> showLoading()
                        is ScreenState.ShowCharacters -> showCharacters(it.list)
                    }
                }
            }
        }
    }

    private fun showLoading() {
        binding.imgSplash.visibility = View.VISIBLE
    }

    private fun showCharacters(list: List<Character>) {
        binding.imgSplash.visibility = View.INVISIBLE
        charactersAdapter.submitList(list)
    }
}
