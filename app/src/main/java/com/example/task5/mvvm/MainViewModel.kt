package com.example.task5.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task5.network.api.CatsApiImpl
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val repository = CatsRepository.get()
    fun addCats() {
        viewModelScope.launch {
            repository.addCats(CatsApiImpl.getListOfCats())
        }
    }

    init {
        viewModelScope.launch {
            repository.initCats(CatsApiImpl.getListOfCats())
        }
    }
}
