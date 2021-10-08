package com.example.task5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.ImageLoader
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

        val adapter = CatsAdapter(ImageLoader.invoke(requireContext())) {
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment("${it.url}")
            this.findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailFragment("${it.url}"))
        }
        binding.list.layoutManager = LinearLayoutManager(this.context)
        binding.list.adapter = adapter
//        viewModel.cats.observe(this.viewLifecycleOwner, Observer {
//            it ?: return@Observer
//            adapter.submitList(it)
//        })
        viewModel.repository.cats.observe(
            this.viewLifecycleOwner,
            Observer {
                it ?: return@Observer
                adapter.submitList(it)
            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        bbinding = null
    }
}
