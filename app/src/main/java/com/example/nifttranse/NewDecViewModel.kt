package com.example.nifttranse

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nifttranse.api.Resource
import com.example.nifttranse.data.Dec
import com.example.nifttranse.data.decRepository

class NewDecViewModel : ViewModel() {

    val DiedsRepository: decRepository = decRepository()

    fun newDec(dec: Dec?): LiveData<Resource<Dec?>>
    {
        return DiedsRepository.newDec(dec)
    }

}