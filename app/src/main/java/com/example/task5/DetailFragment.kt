package com.example.task5

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.fragment.navArgs
import coil.api.load
import com.example.task5.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private val navigationArgs: DetailFragmentArgs by navArgs()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var qw: SaveInterface

    override fun onAttach(context: Context) {
        super.onAttach(context)
        qw = context as SaveInterface
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = navigationArgs.url

        binding.catview.load(url)

        binding.saveButton.setOnClickListener {
            saveToGallery()
        }
    }

    private fun saveToGallery() {

        val image = binding.catview.drawable
        val bitmap = image.toBitmap()

        if (qw.savePhotoToExternalStorage("", bitmap)) {
            binding.saveButton.isEnabled = false
            binding.saveButton.text = "Saved!"
        }
    }
}
