package com.example.shoppinglist.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.ItemShopDisabledBinding
import com.example.shoppinglist.databinding.ItemShopEnabledBinding
import com.example.shoppinglist.domain.ShopItem

class ShopListAdapter :
    ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {

    companion object {
        const val VIEW_TYPE_DISABLED = 0
        const val VIEW_TYPE_ENABLED = 1
        const val MAX_POOL_SIZE = 15
    }

    /*var shopList = listOf<ShopItem>()
        set(value) {
            val callback = ShopListDiffCallback(shopList, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }*/ // not needed for ShopItemDiffCallback implementation

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    private var count = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val layout = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }

        return ShopItemViewHolder(
            DataBindingUtil.inflate<ViewDataBinding>(inflater, layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        Log.d("MainActivityTest", "${++count}")
        val item = getItem(position)
        val binding = holder.binding

        when (binding) {
            is ItemShopDisabledBinding -> {
                binding.tvShopItemName.text = item.name
                binding.tvShopItemCount.text = item.count.toString()
            }
            is ItemShopEnabledBinding -> {
                binding.tvShopItemName.text = item.name
                binding.tvShopItemCount.text = item.count.toString()
            }
        }

        binding.root.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(item)
            true
        }
        binding.root.setOnClickListener {
            onShopItemClickListener?.invoke(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).enabled) {
            false -> VIEW_TYPE_DISABLED
            true -> VIEW_TYPE_ENABLED
        }
    }

    // not needed for ShopItemDiffCallback implementation
    /* override fun getItemCount() = shopList.size */
}