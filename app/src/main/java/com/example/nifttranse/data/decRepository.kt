package com.example.nifttranse.data

import androidx.lifecycle.liveData
import com.example.nifttranse.api.ApiClient2
import com.example.nifttranse.api.Resource
import com.example.nifttranse.api.apiService
import kotlinx.coroutines.Dispatchers
import java.io.IOException

class decRepository {
    fun getListDec() = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        try{
            val api = ApiClient2.createService(apiService::class.java)
            val response = api.getlistDec()
            if(response.isSuccessful) {
                emit(Resource.success(response.body()?.data, response.body()?.responseStatus))
            }
        }
        catch (e: IOException) {
            emit(Resource.error("בעיית תקשורת", null))
        }
    }

    fun updateTag(nift:Int,tag:String) = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        try{
            val api = ApiClient2.createService(apiService::class.java)
            val response = api.updateTag(nift,tag)
            if(response.isSuccessful) {
                emit(Resource.success(response.body()?.data, response.body()?.responseStatus))
            }
        }
        catch (e: IOException) {
            emit(Resource.error("בעיית תקשורת", null))
        }
    }
    fun newDec(Dec:Dec?) = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        try{
            val api = ApiClient2.createService(apiService::class.java)
            val response = api.newDec(Dec)
            if(response.isSuccessful) {
                emit(Resource.success(response.body()?.data, response.body()?.responseStatus))
            }
        }
        catch (e: IOException) {
            emit(Resource.error("בעיית תקשורת", null))
        }
    }
    fun cancelTag(nift: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        try{
            val api = ApiClient2.createService(apiService::class.java)
            val response = api.cancelTag(nift)
            if(response.isSuccessful) {
                emit(Resource.success(response.body()?.data, response.body()?.responseStatus))
            }
        }
        catch (e: IOException) {
            emit(Resource.error("בעיית תקשורת", null))
        }
    }
}