package com.github.vitorfg8.popcorn.home.trends.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.vitorfg8.popcorn.databinding.ItemTrendBinding
import com.github.vitorfg8.popcorn.home.trends.ui.dataUi.TrendDataUi

class InfinitePageAdapter(
    originalList: List<TrendDataUi>,
    private val clickListener: (id: Int, mediaType: String) -> Unit
) : RecyclerView.Adapter<InfinitePageAdapter.TrendsViewHolder>() {

    private val newList: List<TrendDataUi> =
        listOf(originalList.last()) + originalList + listOf(originalList.first())

    class TrendsViewHolder(private val binding: ItemTrendBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(trendDataUi: TrendDataUi, clickListener: (id: Int, mediaType: String) -> Unit) {
            Glide.with(binding.root.context).load(trendDataUi.backdropUrl)
                .into(binding.imageBackdrop)
            binding.textMediaType.text = trendDataUi.mediaType
            binding.textViewVotes.text = trendDataUi.voteAverage
            binding.textViewMediaTitle.text = trendDataUi.title
            binding.root.setOnClickListener {
                clickListener(trendDataUi.id, trendDataUi.mediaType)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendsViewHolder {
        val binding = ItemTrendBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return TrendsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrendsViewHolder, position: Int) {
        holder.bind(newList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return newList.size
    }

}