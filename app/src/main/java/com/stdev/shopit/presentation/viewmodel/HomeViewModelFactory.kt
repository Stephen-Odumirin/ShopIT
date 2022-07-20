package com.stdev.shopit.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stdev.shopit.domain.usecase.GetAllCategoriesUseCase
import com.stdev.shopit.domain.usecase.GetAllProductsUseCase
import com.stdev.shopit.domain.usecase.GetCategoryProductUseCase

class HomeViewModelFactory(
    private val app : Application,
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val getCategoryProductUseCase: GetCategoryProductUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(app, getAllProductsUseCase,getAllCategoriesUseCase,getCategoryProductUseCase) as T
    }

}