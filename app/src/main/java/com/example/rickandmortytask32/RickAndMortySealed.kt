package com.example.rickandmortytask32

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmortytask32.data.DataRickAndMorty
import com.example.rickandmortytask32.databinding.ItemCharacterBinding


sealed class RickAndMortySealed{
    data class CharacterRecyclerRickAndMorty(val characterData: DataRickAndMorty): RickAndMortySealed()
}
class SealedMortyAdapter: ListAdapter<RickAndMortySealed, RecyclerView.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HolderCharacter(ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when(holder){
            is HolderCharacter -> holder.bind(item as RickAndMortySealed.CharacterRecyclerRickAndMorty)
        }

    }

}

class HolderCharacter(binding: ItemCharacterBinding): RecyclerView.ViewHolder(binding.root){
    private val heroType = binding.heroType
    private val heroGender = binding.heroGender
    private val heroImage = binding.heroImage

    fun bind(item: RickAndMortySealed.CharacterRecyclerRickAndMorty){
        heroType.text = item.characterData.type
        heroGender.text = item.characterData.gender

        Glide.with(heroImage.context)
                //https://rickandmortyapi.com/api/character/avatar/3.jpeg
            .load("${item.characterData.image}")
            .into(heroImage)
    }
}

private val diffUtil = object : DiffUtil.ItemCallback<RickAndMortySealed>() {

    override fun areItemsTheSame(oldItem: RickAndMortySealed, newItem: RickAndMortySealed): Boolean {
        val paramIsWeatherPlus: Boolean = oldItem is RickAndMortySealed.CharacterRecyclerRickAndMorty && newItem is RickAndMortySealed.CharacterRecyclerRickAndMorty

        if (paramIsWeatherPlus) {
            oldItem as RickAndMortySealed.CharacterRecyclerRickAndMorty
            newItem as RickAndMortySealed.CharacterRecyclerRickAndMorty
            return oldItem.characterData == newItem.characterData
        }
        return false
    }

    override fun areContentsTheSame(oldItem: RickAndMortySealed, newItem: RickAndMortySealed): Boolean {
        return oldItem == newItem
    }
}

