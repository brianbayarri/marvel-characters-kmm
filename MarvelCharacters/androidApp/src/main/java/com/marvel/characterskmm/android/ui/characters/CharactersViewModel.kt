package com.marvel.characterskmm.android.ui.characters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marvel.characterskmm.domain.services.CharactersService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.marvel.characterskmm.data.Character

class CharactersViewModel(
    private val charactersService: CharactersService
) : ViewModel() {

    private val _screenState: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.Loading)
    val screenState: Flow<ScreenState> = _screenState

    private val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d("CharactersViewModel", "Error retrieving characters: ${throwable.message}")
    }

    init {
        viewModelScope.launch {
            kotlin.runCatching {
                charactersService.getMarvelCharacters()
            }.onSuccess {
                _screenState.value = ScreenState.ShowCharacters(it)
            }.onFailure {

            }
        }
    }

}

sealed class ScreenState {

    object Loading : ScreenState()

    class ShowCharacters(val list: List<Character>) : ScreenState()
}