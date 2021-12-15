package com.example.rickandmortytask32.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortytask32.data.DataRickAndMortyNw
import com.example.rickandmortytask32.data.RickAndMortyApi
import com.example.rickandmortytask32.domain.DataRickAndMorty
import com.example.rickandmortytask32.data.toDomain
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class RickAndMortyViewModel : ViewModel() {
    private val api: RickAndMortyApi = RickAndMortyApi.create()
    private var _characters: MutableLiveData<List<DataRickAndMorty>> =
        MutableLiveData<List<DataRickAndMorty>>()
    val characters: LiveData<List<DataRickAndMorty>> get() = _characters

    init {
        getDataFromNetwork()
    }

    private fun getDataFromNetwork() {
        api.getCharacterInfo(

        ).enqueue(object : Callback<DataRickAndMortyNw> {
            override fun onResponse(
                call: Call<DataRickAndMortyNw>,
                response: Response<DataRickAndMortyNw>
            ) {
                if (response.isSuccessful) {
                    val character: DataRickAndMortyNw = response.body() as DataRickAndMortyNw
                    val characterDomain: List<DataRickAndMorty> = character.list.map {
                        it.toDomain()
                    }
                    _characters.value = characterDomain

                } else Timber.d(response.message())
            }

            override fun onFailure(call: Call<DataRickAndMortyNw>, t: Throwable) {
                Timber.d("CRASHED")
            }
        })
    }
}