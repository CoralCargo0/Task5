package com.example.task5.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cats.data.Cat

const val IMAGE_QUALITY = 100
const val BASE_URL = "https://api.thecatapi.com/v1/"
const val IMAGE_SHORT = "images/search?"
const val API_KEY = "api_key=e82cbab1-915f-4062-a332-2babb784e6a0"
const val ITEMS_COUNT = "limit=20"
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
