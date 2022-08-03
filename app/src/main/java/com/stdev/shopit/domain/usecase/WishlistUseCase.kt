package com.stdev.shopit.domain.usecase

import com.stdev.shopit.data.model.ShopItem
import com.stdev.shopit.domain.repository.ShopRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WishlistUseCase @Inject constructor(
    private val repository: ShopRepository
) {

    suspend fun addToWishlist(shopItem: ShopItem){
        repository.addToWishlist(shopItem)
    }

    suspend fun deleteWishlist(shopItem: ShopItem){
        return repository.deleteWishlistItem(shopItem)
    }

    fun getWishlist() : Flow<List<ShopItem>>{
        return repository.getWishlistItems()
    }

    suspend fun clearWishlist(){
        return repository.clearWishlist()
    }

}