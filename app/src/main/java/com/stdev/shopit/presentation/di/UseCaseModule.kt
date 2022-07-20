package com.stdev.shopit.presentation.di

import com.stdev.shopit.domain.repository.ShopRepository
import com.stdev.shopit.domain.usecase.GetAllProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetAllProductsUseCase(shopRepository: ShopRepository) : GetAllProductsUseCase{
        return GetAllProductsUseCase(shopRepository)
    }

}