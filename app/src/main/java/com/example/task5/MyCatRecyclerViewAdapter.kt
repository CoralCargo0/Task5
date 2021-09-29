package com.example.task5

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import coil.api.load
import com.example.cats.data.Cat

import com.example.task5.databinding.FragmentMainBinding
import kotlinx.coroutines.coroutineScope

class CatsAdapter(private val onItemClicked: (Cat) -> Unit) :
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
            binding.catImageView.load(cat.imageUrl)
        }
    }


    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Cat>() {
            override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}

//class MyCatRecyclerViewAdapter: RecyclerView.Adapter<CatsViewHolder>() {
//
//    private val cats = mutableListOf<Cats>()
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.cat_layout, null)
//        return  CatsViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
//        val imageUrl = cats[position].imageUrl ?: ""
//        holder.bind(imageUrl)
//    }
//
//    override fun getItemCount(): Int {
//        return cats.size
//    }
//
//    fun addCats(newCats: List<Cats>) {
//        cats.addAll(newCats)
//        notifyDataSetChanged()
//    }
//
//}
//
//class CatsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//
//    private val imageView = view.findViewById<ImageView>(R.id.catImageView)
//
//    fun bind(imageUrl: String) {
//        imageView.load(imageUrl)
//    }
//}