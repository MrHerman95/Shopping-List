package com.example.shoppinglist.presentation

import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.ShopListRepositoryImpl
import com.example.shoppinglist.domain.EditShopItemUseCase
import com.example.shoppinglist.domain.GetShopListUseCase
import com.example.shoppinglist.domain.RemoveShopItemUseCase
import com.example.shoppinglist.domain.ShopItem

class MainViewModel: ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val removeShopItemUseCase = RemoveShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    var shopList = getShopListUseCase.getShopList()

    fun removeShopItem(item: ShopItem) {
        removeShopItemUseCase.removeShopItem(item)
    }

    fun changeEnableState(item: ShopItem) {
        val newItem = item.copy(enabled = !item.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }
}