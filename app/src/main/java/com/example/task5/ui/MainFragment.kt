package com.example.task5.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task5.mvvm.MainViewModel
import com.example.task5.databinding.FragmentMainListBinding

class MainFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel>()
    private var bbinding: FragmentMainListBinding? = null
    private val binding get() = bbinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bbinding = FragmentMainListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val adapter = CatsAdapter() {
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment("${it.url}")
            this.findNavController()
                .navigate(MainFragmentDirections.actionMainFragmentToDetailFragment("${it.url}"))
        }
        binding.list.layoutManager = GridLayoutManager(this.context, 2)
        binding.list.adapter = adapter
        viewModel.repository.cats.observe(
            this.viewLifecycleOwner,
            Observer {
                it ?: return@Observer
                adapter.submitList(it)
            }
        )

        binding.list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (isLastItemDisplaying(recyclerView)) {
                    viewModel.addCats()
                }
            }
        })
    }

    private fun isLastItemDisplaying(recyclerView: RecyclerView): Boolean {
        if (recyclerView.adapter!!.itemCount != 0) {
            val lastVisibleItemPosition =
                (recyclerView.layoutManager as GridLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
            if (lastVisibleItemPosition != RecyclerView.NO_POSITION && lastVisibleItemPosition >= recyclerView.adapter!!
                .itemCount - 4
            ) return true
        }
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        bbinding = null
    }
}
