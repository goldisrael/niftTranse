package com.example.nifttranse


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nifttranse.api.Resource
import com.example.nifttranse.data.Dec
import com.example.nifttranse.data.decRepository

class ListViewModel : ViewModel() {
    val DiedsRepository: decRepository = decRepository()

    fun getDec():LiveData<Resource<List<Dec>?>>
    {
        return DiedsRepository.getListDec()
    }
    fun updateTag(nift:Int,tag:String):LiveData<Resource<Dec?>>
    {
        return DiedsRepository.updateTag(nift,tag)
    }
}