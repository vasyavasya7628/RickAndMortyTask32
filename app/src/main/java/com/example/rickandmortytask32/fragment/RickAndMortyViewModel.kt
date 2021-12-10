package com.example.rickandmortytask32.fragment

import androidx.lifecycle.ViewModel
import com.example.rickandmortytask32.data.DataRickAndMorty

class RickAndMortyViewModel: ViewModel() {
    var characters: MutableList<DataRickAndMorty> = mutableListOf()
        private set

    fun setCharacters(character: List<DataRickAndMorty>){
        characters = characters as MutableList<DataRickAndMorty>
    }
}