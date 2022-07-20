package com.stdev.shopit.domain.usecase

import com.stdev.shopit.data.model.Category
import com.stdev.shopit.data.util.Resource
import com.stdev.shopit.domain.repository.ShopRepository

class GetAllCategoriesUseCase(private val repository: ShopRepository) {

    suspend fun execute() : Resource<Category>{
        return repository.getAllCategories()
    }

}