package com.stdev.shopit.presentation.di

import com.stdev.shopit.data.api.ShopApiService
import com.stdev.shopit.data.db.ShopDAO
import com.stdev.shopit.data.repository.datasource.ShopLocalDataSource
import com.stdev.shopit.data.repository.datasource.ShopRemoteDataSource
import com.stdev.shopit.data.repository.datasourceImpl.ShopLocalDataSourceImpl
import com.stdev.shopit.data.repository.datasourceImpl.ShopRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun providesLocalDataSource(shopDAO: ShopDAO) : ShopLocalDataSource {
        return ShopLocalDataSourceImpl(shopDAO)
    }

    @Singleton
    @Provides
    fun provideShopRemoteDataSource(shopApiService: ShopApiService) : ShopRemoteDataSource {
        return ShopRemoteDataSourceImpl(shopApiService)
    }

}