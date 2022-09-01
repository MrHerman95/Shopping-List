package com.example.shoppinglist.domain

class RemoveShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun removeShopItem(item: ShopItem) {
        shopListRepository.removeShopItem(item)
    }
}