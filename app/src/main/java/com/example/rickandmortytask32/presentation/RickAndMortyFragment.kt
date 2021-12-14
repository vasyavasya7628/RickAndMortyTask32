package com.example.rickandmortytask32.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortytask32.databinding.FragmentRickAndMortyBinding
import com.example.rickandmortytask32.domain.DataRickAndMorty

class RickAndMortyFragment : Fragment() {
    private var _binding: FragmentRickAndMortyBinding? = null
    private val binding get() = _binding!!
    private val characterAdapter: CharacterAdapter = CharacterAdapter()
    private val rickAndMortyViewModel: RickAndMortyViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRickAndMortyBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        rickAndMortyViewModel.characters.observe(viewLifecycleOwner) {
            loadDataToAdapter(it)
        }


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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}