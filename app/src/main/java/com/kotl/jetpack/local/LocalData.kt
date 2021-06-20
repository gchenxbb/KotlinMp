package com.kotl.jetpack.local

import android.content.Context
import android.content.SharedPreferences
import com.kotl.jetpack.constants.FAVOURITES_KEY
import com.kotl.jetpack.constants.SHARED_PREFERENCES_FILE_NAME
import com.kotl.jetpack.error.PASS_WORD_ERROR
import com.kotl.jetpack.login.dto.LoginRequest
import com.kotl.jetpack.login.dto.LoginResponse
import com.kotl.jetpack.data.Resource


import javax.inject.Inject

/**
 * Created by chenguang
 */

class LocalData @Inject constructor(val context: Context) {

    fun doLogin(loginRequest: LoginRequest): Resource<LoginResponse> {
        if (loginRequest == LoginRequest("13162683391", "qwe1234")) {
            return Resource.Success(LoginResponse("123", "Ahmed", "Mahmoud",
                    "FrunkfurterAlle", "77", "12000", "Berlin",
                    "Germany", "ahmed@ahmed.ahmed"))
        }
        return Resource.DataError(PASS_WORD_ERROR)
    }

    fun getCachedFavourites(): Resource<Set<String>> {
        val sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, 0)
        return Resource.Success(sharedPref.getStringSet(FAVOURITES_KEY, setOf())
                ?: setOf())
    }

    fun cacheFavourites(ids: Set<String>): Resource<Boolean> {
        val sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, 0)
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putStringSet(FAVOURITES_KEY, ids)
        editor.apply()
        val isSuccess = editor.commit()
        return Resource.Success(isSuccess)
    }

}

