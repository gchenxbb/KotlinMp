package com.kotlin.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.app.demo.KlActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btn_setting: Button
    private lateinit var btn_demo: Button
    private lateinit var btn_applist: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        btn_setting = findViewById(R.id.btn_setting)
        btn_demo = findViewById(R.id.btn_demo)
        btn_applist= findViewById(R.id.btn_applist)
        btn_setting.setOnClickListener { startActivity(Intent(this, SettingActivity::class.java)) }
        btn_demo.setOnClickListener { startActivity(Intent(this, KlActivity::class.java)) }
        btn_applist.setOnClickListener { startActivity(Intent(this, AppActivity::class.java)) }

    }
}