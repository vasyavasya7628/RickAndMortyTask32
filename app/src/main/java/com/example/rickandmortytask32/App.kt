package com.example.rickandmortytask32


import android.app.Application
import com.example.rickandmortytask32.data.RickAndMortyApi
import timber.log.Timber

class App() : Application() {
    companion object{
        private lateinit var api: RickAndMortyApi
    }
    override fun onCreate() {
        api = RickAndMortyApi.create()
        Timber.plant(Timber.DebugTree())
        super.onCreate()
    }

}