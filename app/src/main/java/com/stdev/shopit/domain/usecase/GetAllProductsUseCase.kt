package com.stdev.shopit.domain.usecase

import com.stdev.shopit.data.model.Shop
import com.stdev.shopit.data.util.Resource
import com.stdev.shopit.domain.repository.ShopRepository
import retrofit2.Response

class GetAllProductsUseCase(private val repository: ShopRepository) {

    suspend fun execute() : Resource<Shop>{
        return repository.getAllProducts()
    }

}