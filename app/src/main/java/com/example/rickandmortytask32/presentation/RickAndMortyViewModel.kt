package com.example.rickandmortytask32.presentation

import androidx.lifecycle.ViewModel
import com.example.rickandmortytask32.domain.DataRickAndMorty

class RickAndMortyViewModel: ViewModel() {
    var characters: MutableList<DataRickAndMorty> = mutableListOf()
        private set

    fun setCharacters(character: List<DataRickAndMorty>){
        characters = character as MutableList<DataRickAndMorty>
    }
}