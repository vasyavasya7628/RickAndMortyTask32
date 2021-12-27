package com.example.rickandmortytask32.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmortytask32.data.CharacterInfoNw
import com.example.rickandmortytask32.databinding.ItemCharacterBinding

sealed class CharacterSealed {
    data class Character(
        val next: String,
        val pages: Int,
        val prev: Any,
        val characters: List<Character>
    ) : CharacterSealed()

    data class PageInfoSealed(
        val image: String,
        val type: String,
        val gender: String
    )
}

class CharacterAdapter : ListAdapter<CharacterSealed, CharacterViewHolder>(diffUtil) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is CharacterSealed.Character -> 0

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: CharacterViewHolder, position: Int) {
        viewHolder.bind(getItem(position))
    }
}

class CharacterViewHolder(private val binding: ItemCharacterBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: CharacterInfoNw) {
        binding.heroType.text = item.type
        binding.heroGender.text = item.gender
        Glide.with(binding.heroImage.context)
            .load(item.image)
            .into(binding.heroImage)
    }
}

class NextPageViewHolder() {

}

private val diffUtil = object : DiffUtil.ItemCallback<CharacterSealed>() {

    override fun areItemsTheSame(
        oldItem: CharacterSealed,
        newItem: CharacterSealed
    ): Boolean {
        return oldItem. == newItem.image
    }

    override fun areContentsTheSame(
        oldItem: CharacterSealed,
        newItem: CharacterSealed
    ): Boolean {
        return oldItem == newItem
    }
}

