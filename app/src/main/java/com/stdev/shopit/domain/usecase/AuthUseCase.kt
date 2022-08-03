package com.stdev.shopit.domain.usecase

import android.util.Log
import com.stdev.shopit.data.model.*
import com.stdev.shopit.data.util.Resource
import com.stdev.shopit.domain.repository.ShopRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val repository: ShopRepository
) {

    fun loginUser(username : String, password : String) : Flow<Resource<LoginResponse>> = flow{
        emit(Resource.Loading())
        try {
            val login = Login(username, password)
            val response = repository.loginUser(login)
            Log.i("AuthUseCase","I dey here, ${response.data?.token}")
            emit (response)
        }catch (e : HttpException){
            Log.i("AuthUseCase","${e.localizedMessage}")
            emit (Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }catch (e : IOException){
            Log.i("AuthUseCase","${e.localizedMessage}")
            emit (Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

    fun registerUser(username: String,password: String) : Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        //create a demo user and upload
        val user = User(address = Address(city = "new city", geolocation = Geolocation("10","10"), number = 3, street = "new street", zipcode = "new zipcode"), email = "new email", id = 10, name = Name(firstname = "new firstname", lastname = "new lastname"),password = password, phone = "new phone",username = username, v = 1)
        try {
            val response = repository.registerUser(user)
            emit(response)
        }catch (e : HttpException){
            Log.i("AuthUseCase","${e.localizedMessage}")
            emit (Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
        catch (e : IOException){
            Log.i("AuthUseCase","${e.localizedMessage}")
            emit (Resource.Error("Couldn't reach server. Check your internet connection."))
        }

    }

}