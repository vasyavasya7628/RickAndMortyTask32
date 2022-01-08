package com.example.rickandmortytask32.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class CharacterEntity(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "gender") val gender: String
)

