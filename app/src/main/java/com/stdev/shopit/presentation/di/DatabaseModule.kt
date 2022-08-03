package com.stdev.shopit.presentation.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.stdev.shopit.data.db.Converters
import com.stdev.shopit.data.db.ShopDAO
import com.stdev.shopit.data.db.ShopDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun providesShopDatabase(app : Application,gson : Gson) : ShopDatabase{
        return Room.databaseBuilder(app,ShopDatabase::class.java,"shop_database")
            .fallbackToDestructiveMigration()
            .addTypeConverter(Converters(gson))
            .build()
    }

    @Singleton
    @Provides
    fun providesShopDao(database: ShopDatabase) : ShopDAO{
        return database.shopDao()
    }

}