package com.kotl.jetpack.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotl.jetpack.data.RecipesItem
import com.kotl.jetpack.databinding.ItemListBinding

/**
 * Created by chenguang
 */
class ListRAdapter(private val listViewModel: ListViewModel, private val recipes: List<RecipesItem>) : RecyclerView.Adapter<ListViewHolder>() {

    private val onItemClickListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemSelected(recipe: RecipesItem) {
            listViewModel.openRecipeDetails(recipe)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(recipes[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return if (recipes.isNullOrEmpty()) return 0 else recipes.size

    }
}

