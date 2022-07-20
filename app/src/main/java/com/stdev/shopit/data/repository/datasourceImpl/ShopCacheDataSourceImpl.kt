package com.stdev.shopit.data.repository.datasourceImpl

import com.stdev.shopit.data.model.Shop
import com.stdev.shopit.data.model.ShopItem
import com.stdev.shopit.data.repository.datasource.ShopCacheDataSource


class ShopCacheDataSourceImpl : ShopCacheDataSource {

    private var shopList = Shop()
    private var categoryProducts = Shop()

    override suspend fun getProductsFromCache(): Shop {
        return shopList
    }

    override suspend fun saveProductsToCache(products:Shop) {
        shopList.clear()
        shopList = Shop()
    }

    override suspend fun getCategoryProducts(category: String): Shop {
        return shopList.filter { it.category == category } as Shop
    }

}