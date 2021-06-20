package com.kotl.jetpack.viewmodel.changefrag

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kotl.jetpack.R
import kotlinx.android.synthetic.main.fragment_share_master.*

class MasterFragment : Fragment() {
    var tags = "sharedviewmodeltag"

    companion object {
        fun newInstance() = MasterFragment()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        //布局xml
        val view = inflater.inflate(R.layout.fragment_share_master, null)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myViewModel = ViewModelProvider(
                requireActivity(),
                ViewModelProvider.NewInstanceFactory()
        ).get(SharedViewModel::class.java)
        //设置数据监听
        myViewModel.selected.observe(viewLifecycleOwner, Observer {
            tv_content.text = "value:$it";
        })

        btn_change.setOnClickListener {
            myViewModel.update(10)
        }

        Log.d(tags, myViewModel.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tags, "onDestroy")
    }
}