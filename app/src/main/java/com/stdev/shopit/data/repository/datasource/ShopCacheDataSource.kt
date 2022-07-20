package com.stdev.shopit.data.repository.datasource

import com.stdev.shopit.data.model.Shop
import com.stdev.shopit.data.model.ShopItem

interface ShopCacheDataSource {
    suspend fun getProductsFromCache() : Shop
    suspend fun saveProductsToCache(products : Shop)
    suspend fun getCategoryProducts(category : String) : Shop
}

//suspend fun getAllProducts() : Response<Shop>
//    suspend fun getProduct(itemId : Int) : Response<ShopItem>
//    suspend fun getAllCategories() : Response<Category>
//    suspend fun getCategoryProducts(category : String) : Response<Shop>
//    suspend fun uploadProduct(shopItem : ShopItem) : Response<ShopItem>
//    suspend fun updateProduct(id : Int, shopItem : ShopItem) : Response<ShopItem>
//    suspend fun deleteProduct(id : Int) : Response<ShopItem>
//    suspend fun getCart(id : Int) : Response<Cart>
//    suspend fun addToCart(cartItem : CartItem) : Response<CartItem>
//    suspend fun updateCart(id : Int, cartItem: CartItem) : Response<CartItem>
//    suspend fun deleteCart(id : Int) : Response<CartItem>