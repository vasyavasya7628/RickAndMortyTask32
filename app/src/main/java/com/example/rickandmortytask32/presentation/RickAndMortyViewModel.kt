package com.example.rickandmortytask32.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortytask32.data.CharactersNw
import com.example.rickandmortytask32.data.RickAndMortyApi
import com.example.rickandmortytask32.data.toDomain
import com.example.rickandmortytask32.presentation.model.CharactersUI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
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
        loadCharacters()
    }
    private fun loadNextPage(){

    }
    private fun loadCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = api.getCharacterInfo(currentPage).toDomain()
                val charactersUI: MutableList<CharactersUI> =
                    response.list.map {
                        CharactersUI.Data(it)
                    }.toMutableList()
                charactersUI.add(CharactersUI.NextPage)
                withContext(Dispatchers.Main){
                    _characters.value = charactersUI
                }
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

//    fun loadNextPage() {
//        CoroutineScope(Dispatchers.IO).launch {
//            getDataFromNetwork(currentPage)
//        }
//    }

//    private fun getDataFromNetwork(page: Int) {
//        api.getCharacterInfo(
//            page
//        ).enqueue(object : Callback<CharactersNw> {
//            override fun onResponse(
//                call: Call<CharactersNw>,
//                response: Response<CharactersNw>
//            ) {
//                if (response.isSuccessful) {
//                    val character: CharactersNw = response.body() as CharactersNw
//                    val charactersDomain = character.toDomain()
//                    val newCharacterList: MutableList<CharactersUI> = charactersDomain.list.map {
//                        CharactersUI.Data(it)
//                    }.toMutableList()
//                    val oldCharacterList: MutableList<CharactersUI> =
//                        _characters.value?.toMutableList() ?: mutableListOf()
//                    oldCharacterList.addAll(newCharacterList)
//

//
//                } else Timber.d(response.message())
//            }
//
//            override fun onFailure(call: Call<CharactersNw>, t: Throwable) {
//                Timber.d("CRASHED")
//            }
//        })
//    }
}