package com.example.rickandmortytask32.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.rickandmortytask32.RickAndMortyApi
import com.example.rickandmortytask32.data.CharacterInfoNw
import com.example.rickandmortytask32.data.DataRickAndMortyNw
import com.example.rickandmortytask32.databinding.FragmentRickAndMortyBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class RickAndMortyFragment : Fragment() {
    private var _binding: FragmentRickAndMortyBinding? = null
    private val binding get() = _binding!!
    private val api: RickAndMortyApi = RickAndMortyApi.create()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRickAndMortyBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataFromNetwork()
        showToast("Data loaded from internet")
    }

    private fun showToast(message: String) {
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_SHORT
        ).show()
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
                    val characterDomain: List<CharacterInfoNw> =
                        character.list.map { characterInfoNw -> characterInfoNw }
                    Timber.d(characterDomain.toString())
                }
            }

            override fun onFailure(call: Call<DataRickAndMortyNw>, t: Throwable) {
                Timber.d("CRASHED")
            }
        })
    }

}