package com.example.nifttranse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nifttranse.api.Resource
import com.example.nifttranse.data.Dec
import com.example.nifttranse.data.decRepository

class EndViewModel : ViewModel() {
    var _Died = MutableLiveData<Dec>()
    val DiedsRepository: decRepository = decRepository()
    fun start(died: Dec) {
        _Died.value = died
    }
    fun cancelTag(nift:Int): LiveData<Resource<Dec?>>
    {
        return DiedsRepository.cancelTag(nift)
    }

}