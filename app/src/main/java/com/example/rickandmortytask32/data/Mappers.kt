package com.example.rickandmortytask32.data

import com.example.rickandmortytask32.domain.Character
import com.example.rickandmortytask32.domain.Characters

fun CharactersNw.toDomain(): Characters {
    return Characters(
      next = info.next,
      pages = info.pages,
      list = list.toDomain()
    )
}

private fun  List<CharacterInfoNw>.toDomain(): List<Character> = map {
    Character(
        image = it.image,
        type = it.type,
        gender = it.gender
    )
}

