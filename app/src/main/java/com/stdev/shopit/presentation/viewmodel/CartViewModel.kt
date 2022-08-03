package com.stdev.shopit.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.stdev.shopit.data.model.Cart
import com.stdev.shopit.data.model.CartItem2
import com.stdev.shopit.data.model.Product
import com.stdev.shopit.data.model.ShopItem
import com.stdev.shopit.data.util.Resource
import com.stdev.shopit.data.util.Utils
import com.stdev.shopit.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartUseCase: CartUseCase
) : ViewModel() {

    val totalItems : MutableLiveData<Int> = MutableLiveData()
    val totalItemsPrice : MutableLiveData<Double> = MutableLiveData()

    fun getCartItems() = liveData {
        cartUseCase.getCartItems().collect{
            emit(it)
            computeTotal(it)
        }
    }

    fun computeTotal(cartItems : List<CartItem2>) = viewModelScope.launch(IO){
        var price = 0.00
        cartItems.forEach { product ->
            price += product.price.toDouble()
        }
        totalItemsPrice.postValue(price)
        totalItems.postValue(cartItems.size)
    }

    fun deleteCart(cartItem2: CartItem2) = viewModelScope.launch(IO) {
        cartUseCase.deleteCartItem(cartItem2)
    }

    fun clearCart() = viewModelScope.launch(IO) {
        cartUseCase.clearCart()
    }

    fun increment(cartItem: CartItem2){
        updateProductInCart(quantity = cartItem.quantity + 1, price = cartItem.price.toDouble() + cartItem.pricePerItem,cartItem)
    }

    fun decrement(cartItem: CartItem2){
        if (cartItem.quantity > 1){
            updateProductInCart(quantity = cartItem.quantity -1, price = cartItem.price.toDouble() - cartItem.pricePerItem,cartItem)
        }
    }

    private fun updateProductInCart(quantity: Int, price: Double, cartItem: CartItem2) = viewModelScope.launch(IO){
        val copy = cartItem.copy(price = Utils.formatPrice(price.toString()), quantity = quantity)
        cartUseCase.updateCartItem(copy)
    }
}