package com.example.shoppinglist.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {
    fun addShopItem(item: ShopItem)
    fun removeShopItem(item: ShopItem)
    fun editShopItem(item: ShopItem)
    fun getShopItem(id: Int): ShopItem
    fun getShopList(): LiveData<List<ShopItem>>
}