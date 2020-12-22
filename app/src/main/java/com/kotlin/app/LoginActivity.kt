package com.kotlin.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request

class LoginActivity : AppCompatActivity() {
    private lateinit var login: Button
    private lateinit var et_login: EditText
    private lateinit var tv_tips: TextView
    var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        et_login = findViewById(R.id.et_login)
        login = findViewById(R.id.btn_login)
        tv_tips = findViewById(R.id.tv_tips)

        login.setOnClickListener {
            login()
        }

        context = this
    }

    fun login() {
//        GlobalScope.launch {
//            Log.d("GlobalScope:", "GlobalScope thread::${Thread.currentThread().name}")
//        }
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