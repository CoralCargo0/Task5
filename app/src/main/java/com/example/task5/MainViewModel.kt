package com.example.task5

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task5.api.CatsApiImpl
import com.example.task5.data.CatsRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val repository = CatsRepository.get()

    //    private val _cats = MutableLiveData<List<Cat>>()
//    val cats: LiveData<List<Cat>> get() = _cats
    init {
        viewModelScope.launch {
            repository.initCats(CatsApiImpl.getListOfCats())
            // _cats.value = CatsApiImpl.getListOfCats()
        }
    }
}
