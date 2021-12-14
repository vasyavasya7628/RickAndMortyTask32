package com.example.rickandmortytask32.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmortytask32.domain.DataRickAndMorty
import com.example.rickandmortytask32.databinding.ItemCharacterBinding


data class CharacterRecyclerRickAndMorty(val characterData: DataRickAndMorty)
class CharacterAdapter: ListAdapter<CharacterRecyclerRickAndMorty, RecyclerView.ViewHolder>(
    diffUtil
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HolderCharacter(ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when(holder){
            is HolderCharacter -> holder.bind(item as CharacterRecyclerRickAndMorty)
        }

    }

}

class HolderCharacter(binding: ItemCharacterBinding): RecyclerView.ViewHolder(binding.root){

    private val heroType = binding.heroType
    private val heroGender = binding.heroGender
    private val heroImage = binding.heroImage

    fun bind(item: CharacterRecyclerRickAndMorty){
        heroType.text = item.characterData.type
        heroGender.text = item.characterData.gender
        Glide.with(heroImage.context)
            .load(item.characterData.image)
            .into(heroImage)
    }
}

private val diffUtil = object : DiffUtil.ItemCallback<CharacterRecyclerRickAndMorty>() {

    override fun areItemsTheSame(oldItem: CharacterRecyclerRickAndMorty, newItem: CharacterRecyclerRickAndMorty): Boolean {
        if (oldItem.characterData.image == newItem.characterData.image) {
            return oldItem.characterData.image == newItem.characterData.image
        }
        return false
    }

    override fun areContentsTheSame(oldItem: CharacterRecyclerRickAndMorty, newItem: CharacterRecyclerRickAndMorty): Boolean {
        return oldItem == newItem
    }
}

