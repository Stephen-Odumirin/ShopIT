package com.stdev.shopit.presentation.di

import com.stdev.shopit.data.api.ShopApiService
import com.stdev.shopit.data.repository.datasource.ShopCacheDataSource
import com.stdev.shopit.data.repository.datasource.ShopRemoteDataSource
import com.stdev.shopit.data.repository.datasourceImpl.ShopCacheDataSourceImpl
import com.stdev.shopit.data.repository.datasourceImpl.ShopRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {

    @Singleton
    @Provides
    fun provideShopRemoteDataSource(shopApiService: ShopApiService) : ShopRemoteDataSource{
        return ShopRemoteDataSourceImpl(shopApiService)
    }

    @Singleton
    @Provides
    fun providesShopCacheDataSource() : ShopCacheDataSource{
        return ShopCacheDataSourceImpl()
    }

}