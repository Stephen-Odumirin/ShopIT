package com.stdev.shopit.presentation.di

import android.app.Application
import com.stdev.shopit.domain.usecase.GetAllProductsUseCase
import com.stdev.shopit.presentation.viewmodel.HomeViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun providesHomeViewModelFactory(app : Application, getAllProductsUseCase: GetAllProductsUseCase) : HomeViewModelFactory{
        return HomeViewModelFactory(app, getAllProductsUseCase)
    }

}