package com.example.rickandmortytask32.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmortytask32.databinding.ItemButtonBinding
import com.example.rickandmortytask32.databinding.ItemCharacterBinding
import com.example.rickandmortytask32.presentation.model.CharactersUI

class CharacterAdapter(private val onClickNextPage: () -> Unit) : ListAdapter<CharactersUI, RecyclerView.ViewHolder>(diffUtil) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is CharactersUI.Data -> 0
            is CharactersUI.NextPage -> 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> CharacterViewHolder(
                ItemCharacterBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            1 -> NextPageViewHolder(
                ItemButtonBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw Throwable("ERROR")
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when(holder){
            is CharacterViewHolder -> holder.bind(item as CharactersUI.Data)
            is NextPageViewHolder -> holder.bind(onClickNextPage)
        }
    }
}

class CharacterViewHolder(private val binding: ItemCharacterBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: CharactersUI.Data) {
        binding.heroType.text = item.item.type
        binding.heroGender.text = item.item.gender
        Glide.with(binding.heroImage.context)
            .load(item.item.image)
            .into(binding.heroImage)
    }
}

class NextPageViewHolder(private val binding: ItemButtonBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(onClickNextPage: () -> Unit) {
        binding.btLoad.setOnClickListener {
            onClickNextPage()
        }
    }
}

private val diffUtil = object : DiffUtil.ItemCallback<CharactersUI>() {

    override fun areItemsTheSame(
        oldItem: CharactersUI,
        newItem: CharactersUI
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: CharactersUI,
        newItem: CharactersUI
    ): Boolean {
        return oldItem == newItem
    }
}

