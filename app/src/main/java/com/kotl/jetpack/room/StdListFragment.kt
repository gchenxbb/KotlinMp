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

package com.kotl.jetpack.room

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.kotl.jetpack.R
import kotlinx.android.synthetic.main.fragment_std_listview.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StdListFragment : Fragment() {

    private val TAG = "StdListFragment"
    private val viewModel: StdListViewModel by viewModels {
        Injector.provideStdListViewModelFactory(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        //布局xml
        val view = inflater.inflate(R.layout.fragment_std_listview, null)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_save_data.setOnClickListener {
            var curIndex = viewModel.inde.value
            var y: Int = curIndex!!.plus(1)
            viewModel.inde.value = y

            var std = Student("id$y", "namescd$y", "ma", y)
//            val plantType = object : TypeToken<List<Plant>>() {}.type
            val stdList: List<Student> = listOf(std)

            val database = StuDatabase.getInstance(requireContext().applicationContext)
            GlobalScope.launch {
                database.studentDao().insertAll(stdList)
            }

        }

        var adapter = StdAdapter()
        std_list.adapter = adapter
        viewModel.student.observe(viewLifecycleOwner, Observer { its ->
            adapter.submitList(its)
        })


        observe(viewModel.inde, ::handle)
        viewModel.inde.observe(viewLifecycleOwner, Observer {

            Log.d(TAG, "it:$it")
            it?.let {
                Log.d("liffib", "tc:$it")
            }

        })

        //设置数据监听
//        model.selected.observe(viewLifecycleOwner, Observer {
//            tv_content_detail.text = "value:$it";
//        })
//
//        btn_change.setOnClickListener {
//            model.update(11)
//        }
//        Log.d(tags, model.toString())
    }


//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.filter_zone -> {
//                updateData()
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

//    private fun subscribeUi(adapter: PlantAdapter) {
//        viewModel.student.observe(viewLifecycleOwner) { students ->
//            adapter.submitList(students)
//        }
//    }


    fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
        liveData.observe(this, Observer {
            it?.let { t ->
                action(t)
            }
        })
    }


    private fun handle(status: Int) {
        Log.d("liffib", "ita:$status")
        btn_save_data.text = "入库:$status"
        Log.d("liffib", "t:$status")
    }
}
