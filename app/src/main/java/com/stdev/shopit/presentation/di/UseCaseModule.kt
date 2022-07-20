package com.stdev.shopit.presentation.di

import com.stdev.shopit.domain.repository.ShopRepository
import com.stdev.shopit.domain.usecase.GetAllCategoriesUseCase
import com.stdev.shopit.domain.usecase.GetAllProductsUseCase
import com.stdev.shopit.domain.usecase.GetCategoryProductUseCase
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

    @Singleton
    @Provides
    fun providesGetAllCategoriesUseCase(shopRepository: ShopRepository) : GetAllCategoriesUseCase{
        return GetAllCategoriesUseCase(shopRepository)
    }

    @Singleton
    @Provides
    fun providesGetCategoryProductUseCase(shopRepository: ShopRepository) : GetCategoryProductUseCase{
        return GetCategoryProductUseCase(shopRepository)
    }

}