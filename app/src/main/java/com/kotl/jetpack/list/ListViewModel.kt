package com.kotl.jetpack.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kotl.jetpack.base.BaseViewModel
import com.kotl.jetpack.data.Recipes
import com.kotl.jetpack.data.RecipesItem
import com.kotl.jetpack.data.Resource
import com.kotl.jetpack.event.SingleEvent
import com.kotl.jetpack.repository.DataNetRepository
import com.kotl.jetpack.util.wrapEspressoIdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by chenguang
 */
@HiltViewModel
class ListViewModel @Inject constructor(private val dataRemoteRepository: DataNetRepository) : BaseViewModel() {
    /**
     * Data --> LiveData, Exposed as LiveData, Locally in viewModel as MutableLiveData
     */
    private val recipesLiveDataPrivate = MutableLiveData<Resource<Recipes>>()
    val recipesLiveData: LiveData<Resource<Recipes>> get() = recipesLiveDataPrivate

    private val recipeSearchFoundPrivate: MutableLiveData<RecipesItem> = MutableLiveData()
    val recipeSearchFound: LiveData<RecipesItem> get() = recipeSearchFoundPrivate

    /**
     * UI actions as event, user action is single one time event, Shouldn't be multiple time consumption
     */
    private val openRecipeDetailsPrivate = MutableLiveData<SingleEvent<RecipesItem>>()
    val openRecipeDetails: LiveData<SingleEvent<RecipesItem>> get() = openRecipeDetailsPrivate

    fun getRecipes() {
        viewModelScope.launch {
            recipesLiveDataPrivate.value = Resource.Loading()
            wrapEspressoIdlingResource {
                dataRemoteRepository.requestRecipes().collect {
                    recipesLiveDataPrivate.value = it
                }
            }
        }
    }

    fun openRecipeDetails(recipe: RecipesItem) {
        openRecipeDetailsPrivate.value = SingleEvent(recipe)
    }

}
