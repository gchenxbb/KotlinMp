package com.kotl.jetpack.repository

import com.kotl.jetpack.local.LocalData
import com.kotl.jetpack.login.dto.LoginRequest
import com.kotl.jetpack.login.dto.LoginResponse
import com.kotl.jetpack.data.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Created by chenguang
 */
class LoginDataRepository @Inject constructor(private val localRepository: LocalData, private val ioDispatcher: CoroutineContext)  {

    suspend fun doLogin(loginRequest: LoginRequest): Flow<Resource<LoginResponse>> {
        return flow {
            emit(localRepository.doLogin(loginRequest))
        }.flowOn(ioDispatcher)
    }

    suspend fun addToFavourite(id: String): Flow<Resource<Boolean>> {
        return flow {
            localRepository.getCachedFavourites().let {
                it.data?.toMutableSet()?.let { set ->
                    val isAdded = set.add(id)
                    if (isAdded) {
                        emit(localRepository.cacheFavourites(set))
                    } else {
                        emit(Resource.Success(false))
                    }
                }
                it.exceptionCode?.let { exceptionCode ->
                    emit(Resource.DataError<Boolean>(exceptionCode))
                }
            }
        }.flowOn(ioDispatcher)
    }
}
