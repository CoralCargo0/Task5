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
import com.example.task5.databinding.FragmentMainListBinding
import com.example.task5.isLastItemDisplaying
import com.example.task5.mvvm.MainViewModel

class MainFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel>()
    private var _binding: FragmentMainListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainListBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        bindRecycler()
        return binding.root
    }

    private fun bindRecycler() {
        val recyclerViewAdapter = CatsAdapter {
            this.findNavController()
                .navigate(
                    MainFragmentDirections.actionMainFragmentToDetailFragment(
                        "${it.url}",
                        "${it.id}"
                    )
                )
        }
        binding.list.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = recyclerViewAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (isLastItemDisplaying()) {
                        viewModel.addCats()
                    }
                }
            })
        }
        viewModel.repository.cats.observe(
            this.viewLifecycleOwner,
            Observer {
                it ?: return@Observer
                recyclerViewAdapter.submitList(it)
            }
        )
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
