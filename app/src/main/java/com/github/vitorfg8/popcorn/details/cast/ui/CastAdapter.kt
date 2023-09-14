package com.github.vitorfg8.popcorn.details.cast.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.vitorfg8.popcorn.databinding.ItemCastBinding
import com.github.vitorfg8.popcorn.details.cast.ui.dataui.CastDataUi

class CastAdapter : ListAdapter<CastDataUi, CastAdapter.CastViewHolder>(CastDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val binding = ItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CastViewHolder(private val binding: ItemCastBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(castDataUi: CastDataUi) {
            Glide.with(binding.imageCast.context).load(castDataUi.profileUrl)
                .into(binding.imageCast)
            binding.textViewCharacter.text = castDataUi.character
            binding.textViewActorName.text = castDataUi.name
        }
    }

    private class CastDiffCallback : DiffUtil.ItemCallback<CastDataUi>() {
        override fun areItemsTheSame(oldItem: CastDataUi, newItem: CastDataUi): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CastDataUi, newItem: CastDataUi): Boolean {
            return oldItem == newItem
        }
    }
}
