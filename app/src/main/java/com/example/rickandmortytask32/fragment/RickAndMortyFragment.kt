package com.example.rickandmortytask32.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rickandmortytask32.databinding.FragmentRickAndMortyBinding

class RickAndMortyFragment : Fragment() {
    private var _binding: FragmentRickAndMortyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRickAndMortyBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun getDataFromNetwork() {

    }

}