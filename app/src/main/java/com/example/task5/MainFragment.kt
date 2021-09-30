package com.example.task5

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.cats.data.Cat
import com.example.task5.databinding.FragmentMainBinding
import com.example.task5.databinding.FragmentMainListBinding
import com.example.task5.placeholder.PlaceholderContent


class MainFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel>()


    private var _binding: FragmentMainListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val adapter = CatsAdapter {
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment("${it.url}")
            this.findNavController().navigate(action)
        }
        binding.list.layoutManager = LinearLayoutManager(this.context)
        binding.list.adapter = adapter
        viewModel.cats.observe(this.viewLifecycleOwner, Observer {
            it ?: return@Observer
            adapter.submitList(it)
        })

    //        viewModel.allItems.observe(this.viewLifecycleOwner) { books ->
//            books.let {
//                adapter.submitList(it)
//            }
//        }

    }
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.main_menu, menu)
//
//        menu.findItem(R.id.sort_button).setOnMenuItemClickListener {
//            findNavController().navigate(R.id.action_home_dest_to_sort_dest, null)
//            true
//        }
//
//        menu.findItem(R.id.new_book_button).setOnMenuItemClickListener {
//            findNavController().navigate(R.id.action_home_dest_to_add_dest, null)
//            true
//        }
//
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
    }
}