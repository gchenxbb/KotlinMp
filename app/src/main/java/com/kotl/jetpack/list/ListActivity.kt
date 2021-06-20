package com.kotl.jetpack.list

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.kotl.jetpack.base.BaseActivity
import com.kotl.jetpack.constants.ITEM_KEY
import com.kotl.jetpack.data.Recipes
import com.kotl.jetpack.data.RecipesItem
import com.kotl.jetpack.data.Resource
import com.kotl.jetpack.databinding.ActivityListBinding
import com.kotl.jetpack.kotlindemo.KlActivity
import com.kotl.jetpack.event.SingleEvent
import com.kotl.jetpack.util.*
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by chenguang
 */
@AndroidEntryPoint
class ListActivity : BaseActivity() {
    private lateinit var binding: ActivityListBinding
    private val listViewModel: ListViewModel by viewModels()
    private lateinit var listAdapter: ListRAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutManager = LinearLayoutManager(this)
        binding.rvLit.layoutManager = layoutManager
        binding.rvLit.setHasFixedSize(true)
        listViewModel.getRecipes()
    }

    override fun observeViewModel() {
        observe(listViewModel.recipesLiveData, ::handleRecipesList)
        observe(listViewModel.recipeSearchFound, ::showSearchResult)
        observeEvent(listViewModel.openRecipeDetails, ::navigateToDetailsScreen)
        binding.root.showToast(this, listViewModel.showToast, Snackbar.LENGTH_LONG)
    }

    override fun initViewBinding() {
        binding = ActivityListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun showDataView(show: Boolean) {
        binding.tvNoData.visibility = if (show) View.GONE else View.VISIBLE
        binding.rvLit.visibility = if (show) View.VISIBLE else View.GONE
        binding.pbLoading.toGone()
    }

    private fun showLoadingView() {
        binding.pbLoading.toVisible()
        binding.tvNoData.toGone()
        binding.rvLit.toGone()
    }

    private fun showSearchResult(recipesItem: RecipesItem) {
        listViewModel.openRecipeDetails(recipesItem)
        binding.pbLoading.toGone()
    }

    private fun handleRecipesList(status: Resource<Recipes>) {
        when (status) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> status.data?.let { bindListData(recipes = it) }
            is Resource.DataError -> {
                showDataView(false)
                status.exceptionCode?.let { listViewModel.showToastMessage(it) }
            }
        }
    }

    private fun bindListData(recipes: Recipes) {
        if (!(recipes.recipesList.isNullOrEmpty())) {
            listAdapter = ListRAdapter(listViewModel, recipes.recipesList)
            binding.rvLit.adapter = listAdapter
            showDataView(true)
        } else {
            showDataView(false)
        }
    }

    private fun navigateToDetailsScreen(navigateEvent: SingleEvent<RecipesItem>) {
        navigateEvent.getContentIfNotHandled()?.let {
            val nextScreenIntent = Intent(this, KlActivity::class.java).apply {
                putExtra(ITEM_KEY, it)
            }
            startActivity(nextScreenIntent)
        }
    }

}