package com.stdev.shopit.domain.usecase

import com.stdev.shopit.data.model.Shop
import com.stdev.shopit.data.util.Resource
import com.stdev.shopit.domain.repository.ShopRepository

class GetCategoryProductUseCase(private val shopRepository: ShopRepository) {

    suspend fun execute(category : String) : Resource<Shop>{
        return shopRepository.getCategoryProducts(category)
    }

}