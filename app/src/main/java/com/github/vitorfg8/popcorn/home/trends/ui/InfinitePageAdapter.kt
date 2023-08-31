package com.github.vitorfg8.popcorn.home.trends.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.vitorfg8.popcorn.databinding.ItemTrendBinding
import com.github.vitorfg8.popcorn.home.trends.ui.model.TrendUi

class InfinitePageAdapter(originalList: List<TrendUi>) :
    RecyclerView.Adapter<InfinitePageAdapter.TrendsViewHolder>() {

    private val newList: List<TrendUi> =
        listOf(originalList.last()) + originalList + listOf(originalList.first())

    class TrendsViewHolder(private val binding: ItemTrendBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(trendUi: TrendUi) {
            Glide.with(binding.root.context).load(trendUi.backdropUrl).into(binding.imageBackdrop)
            binding.textMediaType.text = trendUi.mediaType
            binding.textViewVotes.text = trendUi.voteAverage
            binding.textViewMediaTitle.text = trendUi.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendsViewHolder {
        val binding = ItemTrendBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return TrendsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrendsViewHolder, position: Int) {
        holder.bind(newList[position])
    }

    override fun getItemCount(): Int {
        return newList.size
    }

}