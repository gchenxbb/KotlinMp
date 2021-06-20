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

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kotl.jetpack.R
import kotlinx.android.synthetic.main.list_item_student.view.*

class StdAdapter : ListAdapter<Student, RecyclerView.ViewHolder>(StdDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_student, parent, false)
        return PlantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val student = getItem(position)
        (holder as PlantViewHolder).bind(student)
    }

    class PlantViewHolder(
            private val view: View
    ) : RecyclerView.ViewHolder(view) {
        init {
//            binding.setClickListener {
//                binding.plant?.let { plant ->
//                    navigateToPlant(plant, it)
//                }
//            }
        }

        private fun navigateToPlant(
                student: Student,
                view: View
        ) {
//            val direction =
//                HomeViewPagerFragmentDirections.actionViewPagerFragmentToPlantDetailFragment(
//                    plant.plantId
//                )
//            view.findNavController().navigate(direction)
        }

        fun bind(item: Student) {
//            binding.apply {
//                plant = item
//                executePendingBindings()
//            }
            view.txt_name.text = item.name

        }
    }
}

private class StdDiffCallback : DiffUtil.ItemCallback<Student>() {

    override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
        return oldItem.stdId == newItem.stdId
    }

    override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
        return oldItem == newItem
    }
}
