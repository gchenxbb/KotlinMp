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

package com.kotl.jetpack.viewmodel.changefrag

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kotl.jetpack.R
import kotlinx.android.synthetic.main.activity_shared_viewmodel.*

class SharedViewModelActivity : AppCompatActivity() {

    var tags = "sharedviewmodeltag"
    private val shareModel: SharedViewModel by viewModels {
        providerSharedModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_viewmodel)
        replaceFragment(MasterFragment.newInstance())

        btn_login_1.setOnClickListener {
            replaceFragment(MasterFragment.newInstance())
        }

        btn_login_2.setOnClickListener {
            replaceFragment(DetailFragment())
        }

        shareModel.update(1024)

        Log.d(tags, shareModel.toString())
    }

    fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_layout, fragment)
        transaction.commit()
    }

    fun providerSharedModelFactory(activity: SharedViewModelActivity): SharedViewModelFactory {
        return SharedViewModelFactory(activity)
    }
}
