package com.stdev.shopit.domain.repository

import com.stdev.shopit.data.model.*
import com.stdev.shopit.data.util.Resource
import retrofit2.Response

interface ShopRepository {

    suspend fun getAllProducts() : Resource<Shop>
    suspend fun getProduct(itemId : Int) : Resource<ShopItem>
    suspend fun getAllCategories() : Resource<Category>
    suspend fun getCategory(category : String) : Resource<Category>
    suspend fun uploadProduct(shopItem : ShopItem) : Resource<ShopItem>
    suspend fun updateProduct(id : Int, shopItem : ShopItem) : Resource<ShopItem>
    suspend fun deleteProduct(id : Int) : Resource<ShopItem>
    suspend fun getCart(id : Int) : Resource<Cart>
    suspend fun addToCart(cartItem : CartItem) : Resource<CartItem>
    suspend fun updateCart(id : Int, cartItem: CartItem) : Resource<CartItem>
    suspend fun deleteCart(id : Int) : Resource<CartItem>

}