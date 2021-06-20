package com.kotl.jetpack.viewmodel.changefrag

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.kotl.jetpack.R
import kotlinx.android.synthetic.main.fragment_share_detail.*
import kotlinx.android.synthetic.main.fragment_share_detail.btn_change

class DetailFragment : Fragment() {
    var tags = "sharedviewmodeltag"
    private val model: SharedViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        //布局xml
        val view = inflater.inflate(R.layout.fragment_share_detail, null)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //设置数据监听
        model.selected.observe(viewLifecycleOwner, Observer {
            tv_content_detail.text = "value:$it";
        })

        btn_change.setOnClickListener {
            model.update(11)
        }
        Log.d(tags, model.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tags, "onDestroy")
    }
}