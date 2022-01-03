package com.example.rickandmortytask32.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortytask32.databinding.FragmentRickAndMortyBinding

//дизайн
//сделать два типа холдера
//содержащий данные о героях
//содержащий кнопку загрузить
//элементы списка сделать двух типов (используя sealed class)
//данные о героях
//объект кнопка
//при получение данных из сети
//удаляем из списка элементы типа кнопки если есть
//добавляем данные полученные из сети
//добавляем кнопку загрузить в конец списка
//при нажатии на кнопку - получать следующую страницу с данными
//использовать ViewModel и LiveData

class RickAndMortyFragment : Fragment() {
    private var _binding: FragmentRickAndMortyBinding? = null
    private val binding get() = _binding!!
    private val characterAdapter: CharacterAdapter = CharacterAdapter {
          //  rickAndMortyViewModel.loadNextPage()
    }
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
                characterAdapter.submitList(it)
        }


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