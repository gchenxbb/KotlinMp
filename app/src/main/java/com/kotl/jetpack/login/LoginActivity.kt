package com.kotl.jetpack.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import com.kotl.jetpack.MainActivity

import com.kotl.jetpack.base.BaseActivity
import com.kotl.jetpack.data.Resource
import com.kotl.jetpack.databinding.LoginActivityBinding
import com.kotl.jetpack.login.dto.LoginResponse
import com.kotl.jetpack.util.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request

@AndroidEntryPoint
class LoginActivity : BaseActivity() {
    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binding: LoginActivityBinding

    override fun observeViewModel() {
        observe(loginViewModel.loginLiveData, ::handleLoginResult)
        binding.root.setupSnackbar(this, loginViewModel.showSnackBar, Snackbar.LENGTH_LONG)
        binding.root.showToast(this, loginViewModel.showToast, Toast.LENGTH_LONG)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.login.setOnClickListener { doLogin() }
    }

    override fun initViewBinding() {
        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun doLogin() {
        loginViewModel.doLogin(
                binding.username.text.trim().toString(),
                binding.password.text.toString()
        )
    }

    private fun handleLoginResult(status: Resource<LoginResponse>) {
        when (status) {
            is Resource.Loading -> binding.loaderView.toVisible()
            is Resource.Success -> status.data?.let {
                binding.loaderView.toGone()
                navigateToMainScreen()
            }
            is Resource.DataError -> {
                binding.loaderView.toGone()
                status.exceptionCode?.let { loginViewModel.showToastMessage(it) }
            }
        }
    }

    private fun navigateToMainScreen() {
        val nextScreenIntent = Intent(this, MainActivity::class.java)
        startActivity(nextScreenIntent)
        finish()
    }


    private lateinit var login: Button
    private lateinit var et_login: EditText
    private lateinit var tv_tips: TextView
    var context: Context? = null

    fun login() {
        CoroutineScope(Dispatchers.Main).launch {
            val result = async(Dispatchers.IO) {
                val request = Request.Builder().url("https://www.baidu.com").build()
                val response = OkHttpClient().newCall(request).execute()
                response
            }

            var res = result.await()
            tv_tips.text = res.toString()
            Log.d("GlobalScope:", "CoroutineScope thread::${Thread.currentThread().name}")

            startActivity(Intent(context, MainActivity::class.java))
        }

        Log.d("GlobalScope:", " activity thread::${Thread.currentThread().name}")

    }
}