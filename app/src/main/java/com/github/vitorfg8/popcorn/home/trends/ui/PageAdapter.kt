package com.github.vitorfg8.popcorn.home.trends.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.github.vitorfg8.popcorn.databinding.ItemTrendBinding
import com.github.vitorfg8.popcorn.home.trends.data.model.Result


class PageAdapter(private val viewPager2: ViewPager2) :
    ListAdapter<Result, PageAdapter.TrendsHolder>(TrendsDiffUtils()) {

    class TrendsHolder(val binding: ItemTrendBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(trend: Result) {
            Glide.with(binding.root.context).load(trend.backdropPath).into(binding.imageBackdrop)
            binding.textMediaType.text = trend.mediaType
            binding.textViewVotes.text = trend.voteAverage.toString()
        }

        private fun getBackdropUrl(backdropPath: String): String {
            return "" //TODO
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendsHolder {
        val binding = ItemTrendBinding
            .inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return TrendsHolder(binding)
    }

    override fun onBindViewHolder(holder: TrendsHolder, position: Int) {
        holder.bind(getItem(position))
        if (position == currentList.size - 1) {
            //viewPager2.post(runnable)
        }
    }

    /*    private val runnable = Runnable {
            currentList.addAll(currentList)
            notifyDataSetChanged()
        }*/


    class TrendsDiffUtils : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }
}