package com.example.rickandmortytask32.util

import com.example.rickandmortytask32.data.CharacterInfoNw
import com.example.rickandmortytask32.data.DataRickAndMorty

fun CharacterInfoNw.toDomain(): DataRickAndMorty {
    return DataRickAndMorty(
        image = image,
        type = type,
        gender = gender
    )
}


