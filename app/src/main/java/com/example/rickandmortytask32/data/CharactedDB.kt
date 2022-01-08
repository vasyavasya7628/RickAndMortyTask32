package com.example.rickandmortytask32.data

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper

@Database(entities = [CharacterEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase(), CharacterDataBase {
    abstract fun characterDao(): CharacterDao
    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        TODO("Not yet implemented")
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("Not yet implemented")
    }

    override fun clearAllTables() {
        TODO("Not yet implemented")
    }
}

interface CharacterDataBase {
    companion object {
        fun create(context: Context): AppDatabase {
            val db = Room.databaseBuilder(
                context,
                AppDatabase::class.java, "Characters"
            ).build()
            return db
        }
    }

}