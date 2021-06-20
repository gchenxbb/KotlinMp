package com.kotl.jetpack.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.toast

/**
 * Activity抽象基类
 */
abstract class BaseActivity : AppCompatActivity() {

    abstract fun observeViewModel()
    protected abstract fun initViewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
        observeViewModel()
    }

    protected fun showToast(msg: String) {
        runOnUiThread { toast(msg) }
    }
}