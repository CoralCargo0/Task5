package com.example.task5.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.task5.R
import com.example.task5.data.Cat
import com.example.task5.databinding.FragmentMainBinding

class CatsAdapter(
    private val onItemClicked: (Cat) -> Unit
) :
    ListAdapter<Cat, CatsAdapter.CatsViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
        return CatsViewHolder(
            FragmentMainBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current)
    }

    class CatsViewHolder(private var binding: FragmentMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cat: Cat) {
            binding.catImageView.load(cat.url) {
                crossfade(true)
                placeholder(R.drawable.progress)
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Cat>() {
            override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
                return oldItem.url == newItem.url
            }
        }
    }
}
