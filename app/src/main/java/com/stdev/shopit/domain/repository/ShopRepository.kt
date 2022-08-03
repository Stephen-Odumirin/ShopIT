package com.stdev.shopit.domain.repository

import com.stdev.shopit.data.model.*
import com.stdev.shopit.data.util.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import retrofit2.Response


interface ShopRepository {

    suspend fun getAllProducts() : Resource<Shop>
    suspend fun getProduct(itemId : Int) : Resource<ShopItem>
    suspend fun getAllCategories() : Resource<Category>
    suspend fun getCategoryProducts(category : String) : Resource<Shop>
    suspend fun uploadProduct(shopItem : ShopItem) : Resource<ShopItem>
    suspend fun updateProduct(id : Int, shopItem : ShopItem) : Resource<ShopItem>
    suspend fun deleteProduct(id : Int) : Resource<ShopItem>
    suspend fun getCart(id : Int) : Resource<Cart>
    suspend fun getCartProducts(id : Int) : Resource<List<Product>>
    suspend fun addToCart(cartItem : CartItem) : Resource<CartItem>
    suspend fun updateCart(id : Int, cartItem: CartItem) : Resource<CartItem>
    suspend fun deleteCart(id : Int) : Resource<CartItem>
    suspend fun getUser(id : Int) : Resource<User>
    suspend fun updateUser(id : Int,user: User) : Resource<User>
    suspend fun loginUser(login: Login) : Resource<LoginResponse>
    suspend fun registerUser(user : User) : Resource<User>

    suspend fun addToCartItems(cartItem2: CartItem2)
    fun getCartItems() : Flow<List<CartItem2>>
    suspend fun updateCartItems(cartItem2: CartItem2)
    suspend fun deleteCartItems(cartItem2: CartItem2)
    suspend fun clearCart()

    suspend fun addToWishlist(shopItem: ShopItem)
    fun getWishlistItems() : Flow<List<ShopItem>>
    suspend fun deleteWishlistItem(shopItem: ShopItem)
    suspend fun clearWishlist()

}