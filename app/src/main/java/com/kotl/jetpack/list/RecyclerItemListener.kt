package com.kotl.jetpack.list

import com.kotl.jetpack.data.RecipesItem

/**
 * Created by chenguang
 */
interface RecyclerItemListener {
    fun onItemSelected(recipe: RecipesItem)
}
