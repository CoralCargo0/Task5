package com.example.task5

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cats.data.Cat
import com.example.task5.api.CatsApiImpl
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _cats = MutableLiveData<List<Cat>>()
    val cats: LiveData<List<Cat>> get() = _cats
    init {
        viewModelScope.launch {
            _cats.value = CatsApiImpl.getListOfCats()
        }
    }



}