package com.stdev.shopit.data.db

import androidx.room.*
import com.stdev.shopit.data.model.CartItem2
import com.stdev.shopit.data.model.ShopItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ShopDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToCart(cartItem2: CartItem2)

    @Update
    suspend fun updateCart(cartItem2: CartItem2)

    @Delete
    suspend fun deleteCart(cartItem2: CartItem2)

    @Query("select * from cart")
    fun cartItems() : Flow<List<CartItem2>>

    @Query("delete from cart")
    suspend fun clearAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToWishlist(shopItem: ShopItem)

    @Query("select * from wishlist")
    fun wishlistItems() : Flow<List<ShopItem>>

    @Delete
    suspend fun deleteWishlist(shopItem: ShopItem)

    @Query("delete from wishlist")
    suspend fun clearWishlist()


}