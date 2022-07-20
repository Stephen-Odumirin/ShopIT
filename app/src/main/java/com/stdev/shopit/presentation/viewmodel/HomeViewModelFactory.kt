package com.stdev.shopit.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stdev.shopit.domain.usecase.GetAllProductsUseCase

class HomeViewModelFactory(
    private val app : Application,
    private val getAllProductsUseCase: GetAllProductsUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(app, getAllProductsUseCase) as T
    }

}