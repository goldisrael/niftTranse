package com.example.nifttranse.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Dec (
        var nift:Int,
        var cust:Int,
        var firstname:String,
        var lastname:String,
        var name:String,
        var nifttrans:String,
        val levayaTime:String,
        val kvuradate:String
):Parcelable