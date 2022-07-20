package com.stdev.shopit.presentation.di

import com.stdev.shopit.data.repository.ShopRepositoryImpl
import com.stdev.shopit.data.repository.datasource.ShopRemoteDataSource
import com.stdev.shopit.domain.repository.ShopRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providesShopRepository(shopRemoteDataSource: ShopRemoteDataSource) : ShopRepository{
        return ShopRepositoryImpl(shopRemoteDataSource)
    }

}