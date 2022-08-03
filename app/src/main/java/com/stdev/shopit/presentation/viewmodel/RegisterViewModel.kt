package com.stdev.shopit.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stdev.shopit.data.util.Resource
import com.stdev.shopit.data.util.SharedPreference
import com.stdev.shopit.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val sharedPrefUtil: SharedPreference,
) : ViewModel() {

    val successful : MutableLiveData<Boolean?> = MutableLiveData()
    val error : MutableLiveData<String?> = MutableLiveData()

    private fun saveUserAccessToken(token: String) = sharedPrefUtil.saveUserAccessToken(token)

    fun registerUser(username : String, password: String){
        authUseCase.registerUser(username, password).onEach { result ->
            when(result){
                is Resource.Loading -> {
                    Log.i("LoginViewModel","I dey here, Loading")
                }
                is Resource.Error -> {
                    error.postValue("${result.message}")
                    successful.postValue(false)
                    Log.i("LoginViewModel","I dey here, Error ${result.message}")
                }
                is Resource.Success -> {
                    successful.postValue(true)
                    saveUserAccessToken(username)
                    Log.i("LoginViewModel","I dey here, Success ${result.data.toString()}")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun navigated(){
        successful.postValue(null)
        error.postValue(null)
    }

}