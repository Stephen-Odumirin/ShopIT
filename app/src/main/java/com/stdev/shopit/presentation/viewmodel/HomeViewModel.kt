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
import com.stdev.shopit.data.util.Resource
import com.stdev.shopit.domain.usecase.GetAllCategoriesUseCase
import com.stdev.shopit.domain.usecase.GetAllProductsUseCase
import com.stdev.shopit.domain.usecase.GetCategoryProductUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlin.Exception

class HomeViewModel(
    private val app : Application,
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val getCategoryProductUseCase: GetCategoryProductUseCase
) : AndroidViewModel(app){

    val products : MutableLiveData<Resource<Shop>> = MutableLiveData()
    val categories : MutableLiveData<Resource<Category>> = MutableLiveData()

    fun getAllCategories() = viewModelScope.launch(IO) {
        categories.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)){
                val apiResult = getAllCategoriesUseCase.execute()
                categories.postValue(apiResult)
            }else{
                categories.postValue(Resource.Error(message = "Internet not available"))
            }
        }catch (e : Exception){
            categories.postValue(Resource.Error(message = "${e.localizedMessage}"))
        }
    }

    fun getAllProducts() = viewModelScope.launch(IO) {
        products.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)){
                val apiResult = getAllProductsUseCase.execute()
                products.postValue(apiResult)
            }else{
                products.postValue(Resource.Error(message = "Internet not available"))
            }
        }catch (e : Exception){
            products.postValue(Resource.Error(message = "${e.localizedMessage}"))
        }
    }

    fun getCategoryProducts(category : String) = viewModelScope.launch(IO) {
        if(category != "All"){
            products.postValue(Resource.Loading())
            try {
                if (isNetworkAvailable(app)){
                    val apiResult = getCategoryProductUseCase.execute(category)
                    products.postValue(apiResult)
                }else{
                    products.postValue(Resource.Error(message = "Internet not available"))
                }
            }catch (e : Exception){
                products.postValue(Resource.Error(message = "${e.localizedMessage}"))
            }
        }else{
            getAllProducts()
        }

    }

    private fun isNetworkAvailable(context : Context?) : Boolean{
        if(context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null){
                when{
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }

            }
        }
        return true//todo check this shit
    }

}