package com.example.rickandmortytask32.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortytask32.data.CharactersNw
import com.example.rickandmortytask32.data.RickAndMortyApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class RickAndMortyViewModel : ViewModel() {
    private val api: RickAndMortyApi = RickAndMortyApi.create()
    private var _characters: MutableLiveData<CharactersNw> =
        MutableLiveData<CharactersNw>()
    val characters: LiveData<CharactersNw> get() = _characters

    init {
        getDataFromNetwork()
    }

    private fun getDataFromNetwork() {
        api.getCharacterInfo(

        ).enqueue(object : Callback<CharactersNw> {
            override fun onResponse(
                call: Call<CharactersNw>,
                response: Response<CharactersNw>
            ) {
                if (response.isSuccessful) {
                    val character: CharactersNw = response.body() as CharactersNw
                    _characters.value = character

                } else Timber.d(response.message())
            }

            override fun onFailure(call: Call<CharactersNw>, t: Throwable) {
                Timber.d("CRASHED")
            }
        })
    }
}