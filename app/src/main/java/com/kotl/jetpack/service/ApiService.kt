package com.kotl.jetpack.service

import com.kotl.jetpack.data.RecipesItem
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by chenguang
 */
interface ApiService {
    @GET("recipes.json")
    suspend fun fetchRecipes(): Response<List<RecipesItem>>
}
