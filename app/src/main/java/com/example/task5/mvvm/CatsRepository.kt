package com.example.task5.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.task5.data.Cat

const val IMAGE_QUALITY = 100
const val BASE_URL = "https://api.thecatapi.com/v1/"
const val IMAGE_SHORT = "images/search?"
const val API_KEY = "api_key=e82cbab1-915f-4062-a332-2babb784e6a0"
const val ITEMS_COUNT = "limit=20"

object CatsRepository {

    private val mutableCats = MutableLiveData<List<Cat>>()
    val cats: LiveData<List<Cat>> get() = mutableCats

    fun initCats(cats: List<Cat>) {
        mutableCats.value = cats
    }

    fun addCats(cats: List<Cat>) {
        mutableCats.value = mutableCats.value?.plus(cats)
    }
}
