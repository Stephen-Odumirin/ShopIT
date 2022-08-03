package com.stdev.shopit.presentation.di

import android.app.Application
import com.stdev.shopit.data.util.SharedPreference
import com.stdev.shopit.domain.usecase.*
import com.stdev.shopit.presentation.viewmodel.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelModule {

    @Singleton
    @Provides
    fun providesProductDetailViewModel(cartUseCase: CartUseCase,wishlistUseCase: WishlistUseCase) : ProductDetailViewModel{
        return ProductDetailViewModel(cartUseCase,wishlistUseCase)
    }

    @Singleton
    @Provides
    fun providesCartViewModel(cartUseCase: CartUseCase) : CartViewModel{
        return CartViewModel(cartUseCase)
    }

    @Singleton
    @Provides
    fun providesHomeViewModel(app : Application, productUseCase: ProductUseCase) : HomeViewModel{
        return HomeViewModel(app, productUseCase)
    }

    @Singleton
    @Provides
    fun providesProfileViewModel(profileUseCase: ProfileUseCase,sharedPreference: SharedPreference) : ProfileViewModel{
        return ProfileViewModel(profileUseCase,sharedPreference)
    }

    @Singleton
    @Provides
    fun providesEditProfileViewModel(profileUseCase: ProfileUseCase) : EditProfileViewModel{
        return EditProfileViewModel(profileUseCase)
    }

    @Singleton
    @Provides
    fun providesWishlistViewModel(wishlistUseCase: WishlistUseCase) : WishlistViewModel{
        return WishlistViewModel(wishlistUseCase)
    }

    @Singleton
    @Provides
    fun providesLoginViewModel(authUseCase: AuthUseCase,sharedPreference: SharedPreference) : LoginViewModel{
        return LoginViewModel(authUseCase,sharedPreference)
    }

    @Singleton
    @Provides
    fun providesRegisterViewModel(authUseCase: AuthUseCase,sharedPreference: SharedPreference) : RegisterViewModel{
        return RegisterViewModel(authUseCase,sharedPreference)
    }

    @Singleton
    @Provides
    fun providesSplashViewModel(sharedPreference: SharedPreference) : SplashViewModel{
        return SplashViewModel(sharedPreference)
    }

}