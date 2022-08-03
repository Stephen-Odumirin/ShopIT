package com.stdev.shopit.domain.usecase

import com.stdev.shopit.data.model.Category
import com.stdev.shopit.data.model.Shop
import com.stdev.shopit.data.model.ShopItem
import com.stdev.shopit.data.util.Resource
import com.stdev.shopit.domain.repository.ShopRepository
import javax.inject.Inject

class ProductUseCase @Inject constructor(
    private val repository: ShopRepository
) {

    suspend fun getAllProducts() : Resource<Shop> {
        return repository.getAllProducts()
    }

    suspend fun getAllCategories() : Resource<Category>{
        return repository.getAllCategories()
    }

    suspend fun getCategoryProducts(category : String) : Resource<Shop>{
        return repository.getCategoryProducts(category)
    }

}