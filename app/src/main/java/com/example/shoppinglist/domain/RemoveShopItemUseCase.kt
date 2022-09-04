package com.example.shoppinglist.domain

class RemoveShopItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun removeShopItem(item: ShopItem) {
        shopListRepository.removeShopItem(item)
    }
}