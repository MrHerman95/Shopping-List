package com.example.shoppinglist.data

import com.example.shoppinglist.domain.ShopItem

class ShopListMapper {

    fun mapDomainToEntity(shopItem: ShopItem) = ShopItemEntity(
        id = shopItem.id,
        name = shopItem.name,
        count = shopItem.count,
        enabled = shopItem.enabled
    )

    fun mapEntityToDomain(shopItemEntity: ShopItemEntity) = ShopItem(
        id = shopItemEntity.id,
        name = shopItemEntity.name,
        count = shopItemEntity.count,
        enabled = shopItemEntity.enabled
    )

    fun mapListEntityToListDomain(list: List<ShopItemEntity>) = list.map {
        mapEntityToDomain(it)
    }
}