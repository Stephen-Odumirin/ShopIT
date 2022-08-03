package com.stdev.shopit.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stdev.shopit.data.model.User
import com.stdev.shopit.data.util.Resource
import com.stdev.shopit.data.util.SharedPreference
import com.stdev.shopit.domain.usecase.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase,
    private val sharedPreference: SharedPreference
) : ViewModel() {

    val user : MutableLiveData<Resource<User>> = MutableLiveData()

    fun getUser(id : Int) = viewModelScope.launch(IO) {
        user.postValue(Resource.Loading())
        try {
            val apiResult = profileUseCase.getUser(id)
            user.postValue(apiResult)
        }catch (e : Exception){
            user.postValue(Resource.Error(message = e.localizedMessage ?: "Unknown Error"))
        }
    }

    fun logoutUser() = viewModelScope.launch {
        sharedPreference.deleteAccessToken()
    }


}