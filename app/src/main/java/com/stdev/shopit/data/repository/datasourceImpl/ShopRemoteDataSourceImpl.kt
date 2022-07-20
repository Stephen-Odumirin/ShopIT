package com.stdev.shopit.data.repository.datasourceImpl

import com.stdev.shopit.data.api.ShopApiService
import com.stdev.shopit.data.model.*
import com.stdev.shopit.data.repository.datasource.ShopRemoteDataSource
import retrofit2.Response

class ShopRemoteDataSourceImpl(
    private val apiService: ShopApiService
) : ShopRemoteDataSource {
    override suspend fun getAllProducts(): Response<Shop> {
        return apiService.getAllProducts()
    }

    override suspend fun getProduct(itemId: Int): Response<ShopItem> {
        return apiService.getProduct(itemId)
    }

    override suspend fun getAllCategories(): Response<Category> {
        return apiService.getAllCategories()
    }

    override suspend fun getCategory(category: String): Response<Category> {
        return apiService.getCategory(category)
    }

    override suspend fun uploadProduct(shopItem: ShopItem): Response<ShopItem> {
        return apiService.uploadProduct(shopItem)
    }

    override suspend fun updateProduct(id: Int, shopItem: ShopItem): Response<ShopItem> {
        return apiService.updateProduct(id,shopItem)
    }

    override suspend fun deleteProduct(id: Int): Response<ShopItem> {
        return apiService.deleteProduct(id)
    }

    override suspend fun getCart(id: Int): Response<Cart> {
        return apiService.getCart(id)
    }

    override suspend fun addToCart(cartItem: CartItem): Response<CartItem> {
        return apiService.addToCart(cartItem)
    }

    override suspend fun updateCart(id: Int, cartItem: CartItem): Response<CartItem> {
        return apiService.updateCart(id,cartItem)
    }

    override suspend fun deleteCart(id: Int): Response<CartItem> {
        return apiService.deleteCart(id)
    }
}