package com.example.rickandmortytask32.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortytask32.data.RickAndMortyApi
import com.example.rickandmortytask32.data.toDomain
import com.example.rickandmortytask32.presentation.model.CharactersUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class RickAndMortyViewModel : ViewModel() {
    private val api: RickAndMortyApi = RickAndMortyApi.create()
    private var _characters: MutableLiveData<List<CharactersUI>> =
        MutableLiveData<List<CharactersUI>>()
    val characters: LiveData<List<CharactersUI>> get() = _characters
    private var currentPage = 1
    private val MIN_PAGES = 1
    private val MAX_PAGES = 41
    private val PAGES_BOUNDS_LIMIT = 42
    init {
        loadCharacters()
    }

    fun loadNextPage() {
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = api.getCharacterInfo(currentPage).toDomain()
                val charactersUI: MutableList<CharactersUI> =
                    response.list.map {
                        CharactersUI.Data(it)
                    }.toMutableList()
                when {
                    currentPage == MIN_PAGES -> {
                        charactersUI.add(CharactersUI.NextPage)
                        withContext(Dispatchers.Main) {
                            _characters.value = charactersUI
                        }
                        currentPage++
                    }
                    currentPage < MAX_PAGES -> {
                        charactersUI.add(CharactersUI.NextPage)
                        withContext(Dispatchers.Main) {
                            _characters.value = charactersUI
                        }
                        currentPage++
                    }
                    currentPage == PAGES_BOUNDS_LIMIT ->{
                        Timber.d("END OF PAGES")
                    }
                }

            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }
}