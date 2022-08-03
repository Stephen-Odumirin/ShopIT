package com.stdev.shopit.data.repository.datasourceImpl

import com.stdev.shopit.data.api.ShopApiService
import com.stdev.shopit.data.model.*
import com.stdev.shopit.data.repository.datasource.ShopRemoteDataSource
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class ShopRemoteDataSourceImpl @Inject constructor(
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

    override suspend fun getCategoryProducts(category: String): Response<Shop> {
        return apiService.getCategoryProducts(category)
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

    override suspend fun getCartProducts(id: Int): Response<CartItem> {
        return apiService.getCartProducts(id)
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

    override suspend fun getUser(id: Int): Response<User> {
        return apiService.getUser(id)
    }

    override suspend fun updateUser(id: Int,user: User): Response<User> {
        return apiService.updateUser(id,user)
    }

    override suspend fun loginUser(login: Login): Response<LoginResponse> {
        return apiService.loginUser(login)
    }

    override suspend fun registerUser(user: User): Response<User> {
        return apiService.registerUser(user)
    }
}