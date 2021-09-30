package com.example.task5

import android.content.ContentValues
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.fragment.navArgs
import coil.api.load
import com.example.task5.databinding.FragmentDetailBinding
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build

import android.os.Environment
import android.provider.MediaStore
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.lang.Exception


// TODO: Rename parameter arguments, choose names that match


class DetailFragment : Fragment() {

    private val navigationArgs: DetailFragmentArgs by navArgs()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var qw: qwerty

    override fun onAttach(context: Context) {
        super.onAttach(context)
        qw = context as qwerty
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val URL = navigationArgs.url

        binding.catview.load(URL)

        binding.saveButton.setOnClickListener {
            saveToGallery()
        }
    }

    private fun saveToGallery() {

        val image = binding.catview.drawable
        val bitmap = image.toBitmap()

        if(qw.savePhotoToExternalStorage("", bitmap))
        {
            binding.saveButton.isEnabled = false
            binding.saveButton.text = "Saved!"
        }
//        var outputStream: FileOutputStream? = null
//        val file = Environment.DIRECTORY_DCIM
//        val dir = File("$file/Cats")
//        dir.mkdirs()
//
//        val filename = String.format("%d.png", System.currentTimeMillis())
//        val outFile = File(dir, filename)
//        try {
//            outputStream = FileOutputStream(outFile)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
// //       bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
//        try {
//            outputStream?.flush()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        try {
//            outputStream?.close()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
    }

    fun saveMediaToStorage(bitmap: Bitmap) {
        //Generating a file name
        val filename = "${System.currentTimeMillis()}.jpg"

        //Output stream
        var fos: OutputStream? = null

        //For devices running android >= Q
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            //getting the contentResolver
            context?.contentResolver?.also { resolver ->

                //Content resolver will process the contentvalues
                val contentValues = ContentValues().apply {

                    //putting file information in content values
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }

                //Inserting the contentValues to contentResolver and getting the Uri
                val imageUri: Uri? =
                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

                //Opening an outputstream with the Uri that we got
                fos = imageUri?.let { resolver.openOutputStream(it) }
            }
        } else {
            //These for devices running on android < Q
            //So I don't think an explanation is needed here
            val imagesDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, filename)
            fos = FileOutputStream(image)
        }

        fos?.use {
            //Finally writing the bitmap to the output stream that we opened
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)

        }
    }


}