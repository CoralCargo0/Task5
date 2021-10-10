package com.example.task5.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.task5.R
import com.example.task5.databinding.FragmentDetailBinding
import com.example.task5.savePhotoToExternalStorage

class DetailFragment : Fragment() {

    private val navigationArgs: DetailFragmentArgs by navArgs()
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        bindUi()
        return binding.root
    }

    private fun saveToGallery() {
        binding.apply {
            if (catview.drawable.toBitmap()
                .savePhotoToExternalStorage(catId.text.toString(), requireContext())
            ) {
                saveButton.apply {
                    isEnabled = false
                    text = getString(R.string.saved)
                }
            }
        }
    }

    private fun bindUi() {
        binding.apply {
            catview.load(navigationArgs.url)
            catId.text = navigationArgs.id
            saveButton.setOnClickListener {
                saveToGallery()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
