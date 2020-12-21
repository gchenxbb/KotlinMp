package com.kotlin.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var mList: MutableList<Entity>
    private lateinit var mAdapter: ListRecyclerAdapter
    private lateinit var mRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        mList = mutableListOf()

        for (index in 1..20) {
            mList.add(Entity("name:$index", "versionName:$index"))
        }


        mRecyclerView = findViewById(R.id.rv_lit)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = ListRecyclerAdapter(mList)
        mRecyclerView.adapter = mAdapter

        mAdapter.setOnItemClickListener(object : ListRecyclerAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {

            }
        })


    }
}