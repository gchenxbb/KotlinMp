package com.kotlin.app.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.toast

/**
 * Activity抽象基类
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        initView()
        initListeners()
    }

    protected fun initView() {

    }

    protected fun initListeners() {
    }

    protected abstract fun getLayoutId(): Int

    protected fun showToast(msg: String) {
        runOnUiThread { toast(msg) }
    }
}