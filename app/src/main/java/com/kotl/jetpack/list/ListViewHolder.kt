package com.kotl.jetpack.list

import androidx.recyclerview.widget.RecyclerView
import com.kotl.jetpack.data.RecipesItem
import com.kotl.jetpack.databinding.ItemListBinding

/**
 * Created by chenguang
 */
class ListViewHolder(private val itemBinding: ItemListBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(listItem: RecipesItem, recyclerItemListener: RecyclerItemListener) {
        listItem?.run {
            itemBinding.tvAppName.text = listItem.description
            itemBinding.rlItem.setOnClickListener { recyclerItemListener.onItemSelected(listItem) }
        }
    }
}

