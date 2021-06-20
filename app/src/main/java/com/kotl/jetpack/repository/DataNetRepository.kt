package com.kotl.jetpack.repository
import com.kotl.jetpack.data.Recipes
import com.kotl.jetpack.data.Resource
import com.kotl.jetpack.http.NetData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Created by chenguang
 */
class DataNetRepository @Inject constructor(private val remoteRepository: NetData, private val ioDispatcher: CoroutineContext) {

    suspend fun requestRecipes(): Flow<Resource<Recipes>> {
        return flow {
            emit(remoteRepository.requestRecipes())
        }.flowOn(ioDispatcher)
    }
}
