package com.example.rickandmortytask32.data

import com.example.rickandmortytask32.data.CharacterInfoNw
import com.example.rickandmortytask32.domain.DataRickAndMorty

fun CharacterInfoNw.toDomain(): DataRickAndMorty {
    return DataRickAndMorty(
        image = image,
        type = type,
        gender = gender
    )
}


