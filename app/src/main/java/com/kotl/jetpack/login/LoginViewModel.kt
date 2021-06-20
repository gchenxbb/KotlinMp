package com.kotl.jetpack.login

import com.kotl.jetpack.event.SingleEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kotl.jetpack.base.BaseViewModel
import com.kotl.jetpack.data.Resource
import com.kotl.jetpack.error.CHECK_YOUR_FIELDS
import com.kotl.jetpack.error.PASS_WORD_ERROR
import com.kotl.jetpack.error.USER_NAME_ERROR
import com.kotl.jetpack.login.dto.LoginRequest
import com.kotl.jetpack.login.dto.LoginResponse
import com.kotl.jetpack.repository.LoginDataRepository
import com.kotl.jetpack.util.RegexUtils
import com.kotl.jetpack.util.wrapEspressoIdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val dataRepository: LoginDataRepository) : BaseViewModel() {
    private val loginLiveDataPrivate = MutableLiveData<Resource<LoginResponse>>()
    val loginLiveData: LiveData<Resource<LoginResponse>> get() = loginLiveDataPrivate

    private val showSnackBarPrivate = MutableLiveData<SingleEvent<Any>>()
    val showSnackBar: LiveData<SingleEvent<Any>> get() = showSnackBarPrivate

//    private val showToastPrivate = MutableLiveData<SingleEvent<Any>>()
//    val showToast: LiveData<SingleEvent<Any>> get() = showToastPrivate

    fun doLogin(userName: String, passWord: String) {
//        val isUsernameValid = RegexUtils.isValidEmail(userName)
        val isUsernameValid = true
        val isPassWordValid = passWord.trim().length > 4
        if (isUsernameValid && !isPassWordValid) {
            loginLiveDataPrivate.value = Resource.DataError(PASS_WORD_ERROR)
        } else if (!isUsernameValid && isPassWordValid) {
            loginLiveDataPrivate.value = Resource.DataError(USER_NAME_ERROR)
        } else if (!isUsernameValid && !isPassWordValid) {
            loginLiveDataPrivate.value = Resource.DataError(CHECK_YOUR_FIELDS)
        } else {
            println("calling viewModelScope start..")

            CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate).launch {
                println("calling viewModelScope launch1..")
                delay(100)
                println("calling viewModelScope launch2..")
            }
//            viewModelScope.launch {
//                println("calling viewModelScope launch1..")
////                delay(100)
//                loginLiveDataPrivate.value = Resource.Loading()
//                wrapEspressoIdlingResource {
//                    dataRepository.doLogin(loginRequest = LoginRequest(userName, passWord))
//                            .onStart { /**开始前动作*/ }
//                            .catch { /**捕获上游出现的异常*/ }
//                            .onCompletion { /**请求完成*/ }
//                            .collect {
//                                loginLiveDataPrivate.value = it
//                            }
//                }
//                println("calling viewModelScope launch2..")
//            }
            println("calling viewModelScope end..")

        }
    }

//    fun showToastMessage(errorCode: Int) {
//        val error = errorManager.getError(errorCode)
//        showToastPrivate.value = SingleEvent(error.description)
//    }
}