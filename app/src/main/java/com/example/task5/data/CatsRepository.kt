package com.example.task5.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cats.data.Cat

const val IMAGE_QUALITY = 100
const val BASE_URL = "https://api.thecatapi.com/v1/"
const val IMAGE_SHORT = "images/search?"

class CatsRepository {

    val mutableCats = MutableLiveData<List<Cat>>()
    val cats: LiveData<List<Cat>> get() = mutableCats

    fun initCats(cats: List<Cat>) {
        mutableCats.value = cats
    }

    companion object {
        var repo: CatsRepository? = null
        fun get(): CatsRepository {
            if (repo == null) {
                repo = CatsRepository()
                return repo!!
            } else return repo!!
        }
    }
}
