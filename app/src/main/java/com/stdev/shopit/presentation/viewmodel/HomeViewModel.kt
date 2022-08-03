package com.stdev.shopit.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stdev.shopit.data.model.Category
import com.stdev.shopit.data.model.Shop
import com.stdev.shopit.data.util.Network
import com.stdev.shopit.data.util.Network.isNetworkAvailable
import com.stdev.shopit.data.util.Resource
import com.stdev.shopit.domain.usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val app : Application,
    private val productUseCase: ProductUseCase
) : AndroidViewModel(app){

    val products : MutableLiveData<Resource<Shop>> = MutableLiveData()
    val categories : MutableLiveData<Resource<Category>> = MutableLiveData()

    fun getAllCategories() = viewModelScope.launch(IO) {
        categories.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)){
                val apiResult = productUseCase.getAllCategories()
                categories.postValue(apiResult)
            }else{
                categories.postValue(Resource.Error(message = "Internet not available"))
            }
        }catch (e : Exception){
            categories.postValue(Resource.Error(message = "${e.localizedMessage} ?: Unknown Error"))
        }
    }

    fun getAllProducts() = viewModelScope.launch(IO) {
        products.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)){
                val apiResult = productUseCase.getAllProducts()
                products.postValue(apiResult)
            }else{
                products.postValue(Resource.Error(message = "Internet not available"))
            }
        }catch (e : Exception){
            products.postValue(Resource.Error(message = e.localizedMessage ?: "Unknown Error"))
        }
    }

    fun getCategoryProducts(category : String) = viewModelScope.launch(IO) {
        if(category != "All"){
            products.postValue(Resource.Loading())
            try {
                if (isNetworkAvailable(app)){
                    val apiResult = productUseCase.getCategoryProducts(category)
                    products.postValue(apiResult)
                }else{
                    products.postValue(Resource.Error(message = "Internet not available"))
                }
            }catch (e : Exception){
                products.postValue(Resource.Error(message = e.localizedMessage ?: "Unknown Error"))
            }
        }else{
            getAllProducts()
        }
    }

}