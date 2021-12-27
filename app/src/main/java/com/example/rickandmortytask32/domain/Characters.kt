package com.example.rickandmortytask32.domain

data class Characters(
    val next: String,
    val pages: Int,
    val prev: Any,
    val characters: List<Character>
)
