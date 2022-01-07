package com.example.rickandmortytask32


import android.app.Application
import androidx.room.RoomDatabase
import com.example.rickandmortytask32.data.AppDatabase
import com.example.rickandmortytask32.data.CharacterDataBase
import com.example.rickandmortytask32.data.RickAndMortyApi
import timber.log.Timber

class App() : Application() {
    companion object{
        private lateinit var api: RickAndMortyApi
        private lateinit var characterDb: CharacterDataBase
    }
    override fun onCreate() {
        characterDb = CharacterDataBase.create(applicationContext)
        api = RickAndMortyApi.create()
        Timber.plant(Timber.DebugTree())
        super.onCreate()
    }

}