package com.example.rickandmortytask32.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortytask32.data.CharactersNw
import com.example.rickandmortytask32.data.RickAndMortyApi
import com.example.rickandmortytask32.data.toDomain
import com.example.rickandmortytask32.presentation.model.CharactersUI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class RickAndMortyViewModel : ViewModel() {
    private val api: RickAndMortyApi = RickAndMortyApi.create()
    private var _characters: MutableLiveData<List<CharactersUI>> =
        MutableLiveData<List<CharactersUI>>()
    val characters: LiveData<List<CharactersUI>> get() = _characters
    private var currentPage = 1

    init {
        getDataFromNetwork(currentPage)
    }

    fun loadNextPage() {
        getDataFromNetwork(currentPage)
    }

    private fun getDataFromNetwork(page: Int) {
        api.getCharacterInfo(
            page
        ).enqueue(object : Callback<CharactersNw> {
            override fun onResponse(
                call: Call<CharactersNw>,
                response: Response<CharactersNw>
            ) {
                if (response.isSuccessful) {
                    val character: CharactersNw = response.body() as CharactersNw
                    val charactersDomain = character.toDomain()
                    val newCharacterList: MutableList<CharactersUI> = charactersDomain.list.map {
                        CharactersUI.Data(it)
                    }.toMutableList()
                    if (charactersDomain.pages > currentPage) {
                        currentPage++
                        newCharacterList.add(CharactersUI.NextPage)
                    }
                    val oldCharacterList: MutableList<CharactersUI> =
                        _characters.value?.toMutableList() ?: mutableListOf()
                    oldCharacterList.addAll(newCharacterList)

                    if(currentPage > page ){
                        oldCharacterList.remove(CharactersUI.NextPage)
                        _characters.value = newCharacterList
                    }





                } else Timber.d(response.message())
            }

            override fun onFailure(call: Call<CharactersNw>, t: Throwable) {
                Timber.d("CRASHED")
            }
        })
    }
}