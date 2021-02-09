package com.example.nifttranse.api

import androidx.annotation.Keep
import com.example.nifttranse.data.Dec
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface apiService {

    @GET("read.php/?req=offDec")
    suspend fun getlistDec(): Response<ServiceResponse<List<Dec>>>

    @GET("update.php/?req=updateTag")
    suspend fun updateTag(@Query("nift") nift: Int, @Query("tag") tag: String): Response<ServiceResponse<Dec>>

    @POST("create.php/?req=newDec")
    suspend fun newDec(@Body dec:Dec?): Response<ServiceResponse<Dec>>

    @GET("delete.php/?req=cancelTag")
    suspend fun cancelTag(@Query("nift") nift: Int): Response<ServiceResponse<Dec>>
}
@Keep
data class ServiceResponse<T>(
        @SerializedName("data") val data: T,
        @SerializedName("responseStatus") val responseStatus: String,
        @SerializedName("responseMessege") val responseMessege: String
)

