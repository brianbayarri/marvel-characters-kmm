package com.marvel.characterskmm.android.ui.characters

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.marvel.characterskmm.android.R
import com.marvel.characterskmm.android.databinding.ActivityCharactersBinding
import com.marvel.characterskmm.android.domain.adapters.CharactersAdapter
import com.marvel.characterskmm.android.domain.utils.VerticalSpaceItemDecoration
import com.marvel.characterskmm.android.ui.error.ErrorActivity
import com.marvel.characterskmm.data.Character
import kotlinx.coroutines.launch

class CharactersActivity : AppCompatActivity() {

    private lateinit var charactersAdapter: CharactersAdapter
    private lateinit var binding: ActivityCharactersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                        ScreenState.Error -> showErrorScreen()
                        is ScreenState.ShowCharacters -> showCharacters(it.list)
                    }
                }
            }
        }
    }

    private fun showErrorScreen() {
        val errorActivityIntent = Intent(this, ErrorActivity::class.java)
        startActivity(errorActivityIntent)
    }

    private fun showLoading() {
        binding.imgSplash.visibility = View.VISIBLE
    }

    private fun showCharacters(list: List<Character>) {
        binding.imgSplash.visibility = View.INVISIBLE
        charactersAdapter.submitList(list)
    }
}