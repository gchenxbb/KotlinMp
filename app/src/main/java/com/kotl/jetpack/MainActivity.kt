package com.kotl.jetpack

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kotl.jetpack.list.ListActivity
import com.kotl.jetpack.databinding.DatabindingActivity
import com.kotl.jetpack.databindingsample.DatabindingSampleActivity
import com.kotl.jetpack.kotlindemo.KlActivity
import com.kotl.jetpack.hilt.HiltDemoActivity
import com.kotl.jetpack.lifecycle.LifecycleActivity
import com.kotl.jetpack.login.LoginActivity
import com.kotl.jetpack.navigation.NavigationActivity
import com.kotl.jetpack.room.StdRoomActivity
import com.kotl.jetpack.viewmodel.ViewModelActivity
import com.kotl.jetpack.viewmodel.changefrag.SharedViewModelActivity
import com.kotl.jetpack.workmanager.WorkManagerActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        btn_lifecycle?.setOnClickListener { startActivity(Intent(this, LifecycleActivity::class.java)) }
        btn_viewmodel?.setOnClickListener { startActivity(Intent(this, ViewModelActivity::class.java)) }
        btn_vm_changefrag?.setOnClickListener { startActivity(Intent(this, SharedViewModelActivity::class.java)) }
        btn_room?.setOnClickListener { startActivity(Intent(this, StdRoomActivity::class.java)) }
        btn_databinding?.setOnClickListener { startActivity(Intent(this, DatabindingActivity::class.java)) }
        btn_databinding_sample?.setOnClickListener { startActivity(Intent(this, DatabindingSampleActivity::class.java)) }
        btn_navigation?.setOnClickListener { startActivity(Intent(this, NavigationActivity::class.java)) }
        btn_workmanager?.setOnClickListener { startActivity(Intent(this, WorkManagerActivity::class.java)) }
        btn_login?.setOnClickListener { startActivity(Intent(this, LoginActivity::class.java)) }
        btn_demo?.setOnClickListener { startActivity(Intent(this, KlActivity::class.java)) }
        btn_applist?.setOnClickListener { startActivity(Intent(this, ListActivity::class.java)) }
        btn_hilt?.setOnClickListener { startActivity(Intent(this, HiltDemoActivity::class.java)) }


    }
}