package com.stdev.shopit.data.repository

import com.stdev.shopit.data.model.*
import com.stdev.shopit.data.repository.datasource.ShopRemoteDataSource
import com.stdev.shopit.data.util.Resource
import com.stdev.shopit.domain.repository.ShopRepository
import retrofit2.Response

class ShopRepositoryImpl(
    private val remoteDataSource: ShopRemoteDataSource
) : ShopRepository {
    override suspend fun getAllProducts(): Resource<Shop> {
        return responseToShopResult(remoteDataSource.getAllProducts())
    }

    override suspend fun getProduct(itemId: Int): Resource<ShopItem> {
        return responseToShopItemResult(remoteDataSource.getProduct(itemId))
    }

    override suspend fun getAllCategories(): Resource<Category> {
        return responseToCategoryResult(remoteDataSource.getAllCategories())
    }

    override suspend fun getCategory(category: String): Resource<Category> {
        return responseToCategoryResult(remoteDataSource.getCategory(category))
    }

    override suspend fun uploadProduct(shopItem: ShopItem): Resource<ShopItem> {
        return responseToShopItemResult(remoteDataSource.uploadProduct(shopItem))
    }

    override suspend fun updateProduct(id: Int, shopItem: ShopItem): Resource<ShopItem> {
        return responseToShopItemResult(remoteDataSource.updateProduct(id,shopItem))
    }

    override suspend fun deleteProduct(id: Int): Resource<ShopItem> {
        return responseToShopItemResult(remoteDataSource.deleteProduct(id))
    }

    override suspend fun getCart(id: Int): Resource<Cart> {
        return responseToCartResult(remoteDataSource.getCart(id))
    }

    override suspend fun addToCart(cartItem: CartItem): Resource<CartItem> {
        return responseToCartItemResult(remoteDataSource.addToCart(cartItem))
    }

    override suspend fun updateCart(id: Int, cartItem: CartItem): Resource<CartItem> {
        return responseToCartItemResult(remoteDataSource.updateCart(id,cartItem))
    }

    override suspend fun deleteCart(id: Int): Resource<CartItem> {
        return responseToCartItemResult(remoteDataSource.deleteCart(id))
    }

    private fun responseToShopResult(response: Response<Shop>) : Resource<Shop>{
        if (response.isSuccessful){
            response.body()?.let { result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    private fun responseToShopItemResult(response: Response<ShopItem>) : Resource<ShopItem>{
        if (response.isSuccessful){
            response.body()?.let { result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    private fun responseToCartResult(response: Response<Cart>) : Resource<Cart>{
        if (response.isSuccessful){
            response.body()?.let { result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    private fun responseToCartItemResult(response: Response<CartItem>) : Resource<CartItem>{
        if (response.isSuccessful){
            response.body()?.let { result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    private fun responseToCategoryResult(response: Response<Category>) : Resource<Category>{
        if (response.isSuccessful){
            response.body()?.let { result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }



}