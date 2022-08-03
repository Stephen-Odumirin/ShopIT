package com.stdev.shopit.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.stdev.shopit.data.model.ShopItem
import com.stdev.shopit.domain.usecase.WishlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val wishlistUseCase: WishlistUseCase
) : ViewModel() {

    fun getWishlist() = liveData {
        wishlistUseCase.getWishlist().collect{
            emit(it)
        }
    }

    fun deleteWishlist(shopItem: ShopItem) = viewModelScope.launch(IO) {
        wishlistUseCase.deleteWishlist(shopItem)
    }

    fun clearWishlist() = viewModelScope.launch(IO) {
        wishlistUseCase.clearWishlist()
    }


}