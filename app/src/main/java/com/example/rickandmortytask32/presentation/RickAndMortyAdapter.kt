package com.example.rickandmortytask32.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmortytask32.databinding.ItemCharacterBinding
import com.example.rickandmortytask32.domain.DataRickAndMorty

class CharacterAdapter : ListAdapter<DataRickAndMorty, HolderCharacter>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderCharacter {
        return HolderCharacter(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HolderCharacter, position: Int) {
        holder.bind(getItem(position))
    }
}

class HolderCharacter(private val binding: ItemCharacterBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: DataRickAndMorty) {
        binding.heroType.text = item.type
        binding.heroGender.text = item.gender
        Glide.with(binding.heroImage.context)
            .load(item.image)
            .into(binding.heroImage)
    }
}

private val diffUtil = object : DiffUtil.ItemCallback<DataRickAndMorty>() {

    override fun areItemsTheSame(
        oldItem: DataRickAndMorty,
        newItem: DataRickAndMorty
    ): Boolean {
        return oldItem.image == newItem.image
    }

    override fun areContentsTheSame(
        oldItem: DataRickAndMorty,
        newItem: DataRickAndMorty
    ): Boolean {
        return oldItem == newItem
    }
}

