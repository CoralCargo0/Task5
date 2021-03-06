package com.example.task5.network.api

import com.example.task5.data.Cat
import com.example.task5.mvvm.API_KEY
import com.example.task5.mvvm.BASE_URL
import com.example.task5.mvvm.IMAGE_SHORT
import com.example.task5.mvvm.ITEMS_COUNT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface CatsAPI {
    @GET(IMAGE_SHORT + ITEMS_COUNT + "&" + API_KEY)
    suspend fun getListOfCats(): List<Cat>
}

object CatsApiImpl {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val catsService = retrofit.create(CatsAPI::class.java)

    suspend fun getListOfCats(): List<Cat> {
        return withContext(Dispatchers.IO) {
            catsService.getListOfCats()
        }
    }
}
