package com.stdev.shopit.domain.usecase

import com.stdev.shopit.data.model.User
import com.stdev.shopit.data.util.Resource
import com.stdev.shopit.domain.repository.ShopRepository
import javax.inject.Inject

class ProfileUseCase @Inject constructor(private val repository: ShopRepository) {

    suspend fun getUser(id : Int) : Resource<User>{
        return repository.getUser(id)
    }

    suspend fun updateUser(id : Int,user: User): Resource<User> {
        return repository.updateUser(id,user)
    }

}