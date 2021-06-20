/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kotl.jetpack.viewmodel

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.kotl.jetpack.R
import kotlinx.android.synthetic.main.activity_viewmodel.*
import java.util.*

class ViewModelActivity : AppCompatActivity() {

    var tag = "tagviewmodelactivity"
    private val viewmodel: UserViewModel by viewModels {
        providerUserViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewmodel)
        btn_login_.setOnClickListener(View.OnClickListener {
            viewmodel.loginStatus.value = true
        })

        btn_logout_.setOnClickListener(View.OnClickListener {
            viewmodel.loginStatus.value = false
        })

        btn_saved_value_.setOnClickListener(View.OnClickListener {
            viewmodel.setValue(100)
        })
        btn_reset_value_.setOnClickListener(View.OnClickListener {
            viewmodel.setValue(-1)
        })

        viewmodel.getUsers().observe(this, Observer {

            var str = StringBuilder()
            for (value in it) {
                str.append(value.name + '-')
                print("myvalue:${value.id}")
            }
            tv_load_info.setText(str)
        })

        viewmodel.loginStatus.observe(this, Observer {
            Log.d(tag, it.toString())
            tv_boolean_info_.setText("登录状态：$it")
        })

        viewmodel.savedValue.observe(this, Observer {
            Log.d(tag, it.toString())
            tv_number_info_.setText("$it")
        })

        Log.d(tag, viewmodel.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(tag, "onRestoreInstanceState")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(tag, "onSaveInstanceState")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy")
    }

    fun providerUserViewModelFactory(activity: ViewModelActivity): UserViewModelFactory {
        return UserViewModelFactory(activity)
    }
}