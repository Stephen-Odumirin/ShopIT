package com.stdev.shopit.data.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

sealed class Resource<T> (
    val data : T? = null,
    val message : String? = null
){
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data : T? = null) : Resource<T>(data)
    class Error<T>(message : String, data: T? = null) : Resource<T>(data,message)
}
