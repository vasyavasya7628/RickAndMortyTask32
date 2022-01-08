package com.example.rickandmortytask32.data

import androidx.room.*

@Dao
interface CharacterDao {
    @Query("SELECT * FROM characterEntity")
    fun getAll(): List<CharacterEntity>

    @Query("SELECT * FROM characterEntity WHERE uid IN (:characterEntityIds)")
    fun loadAllByIds(characterEntityIds: IntArray): List<CharacterEntity>

     @Query("SELECT * FROM characterEntity WHERE image LIKE :image AND" + ":type AND" + "gender LIKE :last LIMIT 1")
     fun findByImage(image: String, type: String, gender: String): CharacterEntity

     @Insert
     fun insertAll(vararg characters: CharacterEntity)

     @Delete
     fun delete(characterEntity: CharacterEntity)
}



