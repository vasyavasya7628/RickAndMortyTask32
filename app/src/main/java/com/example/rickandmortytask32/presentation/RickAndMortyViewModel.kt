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
                    currentPage == 1 -> {
                        charactersUI.add(CharactersUI.NextPage)
                        withContext(Dispatchers.Main) {
                            _characters.value = charactersUI
                        }
                        currentPage++
                    }
                    currentPage < 41 -> {
                        charactersUI.add(CharactersUI.NextPage)
                        withContext(Dispatchers.Main) {
                            _characters.value = charactersUI
                        }
                        currentPage++
                        Timber.d(currentPage.toString() + "PAGEPAGE")
                    }
                    currentPage == 42 ->{
                        Timber.d("END OF PAGES")
                    }
                }

            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }
}