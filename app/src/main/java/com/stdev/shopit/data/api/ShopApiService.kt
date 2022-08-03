package com.stdev.shopit.data.api

import com.stdev.shopit.data.model.*
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ShopApiService {

    @GET("/products")
    suspend fun getAllProducts() : Response<Shop>

    @GET("products/{id}")
    suspend fun getProduct(@Path(value = "id") itemId : Int) : Response<ShopItem>

    @GET("products/categories")
    suspend fun getAllCategories() : Response<Category>

    @GET("products/category/{category}")
    suspend fun getCategoryProducts(@Path(value = "category") category : String) : Response<Shop>

    @POST("products")
    suspend fun uploadProduct(@Body shopItem : ShopItem) : Response<ShopItem>

    @PATCH("products/{id}")
    suspend fun updateProduct(@Path(value = "id") id : Int, @Body shopItem : ShopItem) : Response<ShopItem>

    @DELETE("products/{id}")
    suspend fun deleteProduct(@Path(value = "id") id : Int) : Response<ShopItem>

    @GET("carts/user/{id}")
    suspend fun getCart(@Path(value = "id") id : Int) : Response<Cart>

    @GET("carts/user/{id}")
    suspend fun getCartProducts(@Path(value = "id") id : Int) : Response<CartItem>

    @POST("carts")
    suspend fun addToCart(@Body cartItem : CartItem) : Response<CartItem>

    @PATCH("carts/{id}")
    suspend fun updateCart(@Path(value = "id") id : Int, @Body cartItem: CartItem) : Response<CartItem>

    @DELETE("carts/{id}")
    suspend fun deleteCart(@Path(value = "id") id : Int) : Response<CartItem>

    @GET("users/{id}")
    suspend fun getUser(@Path(value = "id") id : Int) : Response<User>

    @PUT("users/{id}")
    suspend fun updateUser(@Path(value = "id") id : Int, @Body user: User) : Response<User>

    //Login user
    @POST("auth/login")
    suspend fun loginUser(@Body login : Login): Response<LoginResponse>

    //Register user
    @POST("users")
    suspend fun registerUser(@Body user : User): Response<User>

}