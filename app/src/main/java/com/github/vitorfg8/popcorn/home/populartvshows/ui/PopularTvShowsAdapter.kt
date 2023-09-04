package com.github.vitorfg8.popcorn.home.populartvshows.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.vitorfg8.popcorn.databinding.ItemPosterBinding
import com.github.vitorfg8.popcorn.home.populartvshows.ui.dataUi.PopularTvShowDataUi

class PopularTvShowsAdapter(private val clickListener: (movieId: Int) -> Unit) :
    ListAdapter<PopularTvShowDataUi, PopularTvShowsAdapter.ViewHolder>(PopularTvShowsDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPosterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class ViewHolder(private val binding: ItemPosterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(popularMovie: PopularTvShowDataUi, clickListener: (movieId: Int) -> Unit) {
            binding.textMovieTitle.text = popularMovie.title
            Glide.with(binding.imageMoviePoster.context).load(popularMovie.posterUrl)
                .into(binding.imageMoviePoster)
            binding.root.setOnClickListener {
                clickListener.invoke(popularMovie.id)
            }
        }
    }

    class PopularTvShowsDiffUtils : DiffUtil.ItemCallback<PopularTvShowDataUi>() {
        override fun areItemsTheSame(
            oldItem: PopularTvShowDataUi,
            newItem: PopularTvShowDataUi
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PopularTvShowDataUi,
            newItem: PopularTvShowDataUi
        ): Boolean {
            return oldItem == newItem
        }
    }
}