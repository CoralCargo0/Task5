package com.example.task5

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import coil.ImageLoader
import coil.load
import com.example.cats.data.Cat
import com.example.task5.data.CatsRepository
import com.example.task5.api.CatsApiImpl
import com.example.task5.databinding.FragmentMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CatsAdapter(
    private val imageLoader: ImageLoader,
    private val onItemClicked: (Cat) -> Unit
) :
    ListAdapter<Cat, CatsAdapter.CatsViewHolder>(DiffCallback) {

    val repo = CatsRepository.get()

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

            // holder.bindi.catImageView.animate().rotationY(180f).duration = 500
            // holder.bindi.catImageView.isClickable = true
            onItemClicked(current)
        }
        holder.bind(current, imageLoader)
        if (position % 20 == 15) {
            GlobalScope.launch {
                repo.mutableCats.postValue(
                    repo.mutableCats.value?.plus(CatsApiImpl.getListOfCats())
                )
            }
        }
    }

    class CatsViewHolder(private var binding: FragmentMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val bindi = binding
        fun bind(cat: Cat, imageLoader: ImageLoader) {
            binding.catImageView.loadWithLoader(
                imageLoader,
                cat.url!!,
                R.drawable.giphy
            )
        // binding.catImageView.load(cat.url)
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Cat>() {
            override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}
