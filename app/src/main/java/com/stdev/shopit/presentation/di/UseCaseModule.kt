package com.stdev.shopit.presentation.di

import android.provider.ContactsContract
import com.stdev.shopit.domain.repository.ShopRepository
import com.stdev.shopit.domain.usecase.*
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
    fun providesCartUseCase(repository: ShopRepository) : CartUseCase{
        return CartUseCase(repository)
    }

    @Singleton
    @Provides
    fun providesProductUseCase(repository: ShopRepository) : ProductUseCase{
        return ProductUseCase(repository)
    }

    @Singleton
    @Provides
    fun providesProfileUseCase(repository: ShopRepository) : ProfileUseCase{
        return ProfileUseCase(repository)
    }

    @Singleton
    @Provides
    fun providesAuthUseCase(repository: ShopRepository) : AuthUseCase{
        return AuthUseCase(repository)
    }

}