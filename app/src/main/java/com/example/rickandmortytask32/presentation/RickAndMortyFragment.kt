package com.example.rickandmortytask32.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortytask32.R
import com.example.rickandmortytask32.data.DataRickAndMortyNw
import com.example.rickandmortytask32.data.RickAndMortyApi
import com.example.rickandmortytask32.databinding.FragmentRickAndMortyBinding
import com.example.rickandmortytask32.domain.DataRickAndMorty
import com.example.rickandmortytask32.util.toDomain
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class RickAndMortyFragment : Fragment() {
    private var _binding: FragmentRickAndMortyBinding? = null
    private val binding get() = _binding!!
    private val api: RickAndMortyApi = RickAndMortyApi.create()
    private val characterAdapter: CharacterAdapter = CharacterAdapter()
    private lateinit var rickAndMortyViewModel: RickAndMortyViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRickAndMortyBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rickAndMortyViewModel = ViewModelProvider(this).get(RickAndMortyViewModel::class.java)
        initAdapter()
        if (savedInstanceState == null) {
            showToast(getString(R.string.loaded_from_net))
            getDataFromNetwork()
        } else {
            showToast(getString(R.string.loaded_from_local_storage))
            loadDataToAdapter(rickAndMortyViewModel.characters)
        }
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
                    rickAndMortyViewModel.setCharacters(characterDomain)
                    loadDataToAdapter(characterDomain)
                    Timber.d(characterDomain.toString())

                } else Timber.d(response.message())
            }

            override fun onFailure(call: Call<DataRickAndMortyNw>, t: Throwable) {
                Timber.d("CRASHED")
            }
        })
    }

    fun loadDataToAdapter(characterDomain: List<DataRickAndMorty>) {
        val list = mutableListOf<CharacterRecyclerRickAndMorty>()
        characterDomain.map { characterInfo ->
            list.add(CharacterRecyclerRickAndMorty(characterInfo))
        }
        characterAdapter.submitList(list.toMutableList())
    }

    private fun initAdapter() {
        binding.recyclerviewRickandmorty.layoutManager = LinearLayoutManager(activity)
        binding.recyclerviewRickandmorty.adapter = characterAdapter
    }

    private fun showToast(message: String) {
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}