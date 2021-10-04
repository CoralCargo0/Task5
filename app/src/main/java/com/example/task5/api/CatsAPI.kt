package com.example.task5.api

import com.example.cats.data.Cat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface CatsAPI {
    @GET("images/search?limit=20")
    suspend fun getListOfCats(): List<Cat>
}

object CatsApiImpl {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.thecatapi.com/v1/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val catsService = retrofit.create(CatsAPI::class.java)

    suspend fun getListOfCats(): List<Cat> {
        return withContext(Dispatchers.IO) {
            catsService.getListOfCats()
        }
    }
}
