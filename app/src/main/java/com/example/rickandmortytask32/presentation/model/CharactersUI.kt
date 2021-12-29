package com.example.rickandmortytask32.presentation.model

import com.example.rickandmortytask32.domain.Character

sealed class CharactersUI{
    data class Data(
        val item: Character
    ): CharactersUI()

    object NextPage: CharactersUI()
}
