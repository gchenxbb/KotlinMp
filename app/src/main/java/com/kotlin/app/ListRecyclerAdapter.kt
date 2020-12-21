package com.kotlin.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListRecyclerAdapter(private val appList: MutableList<Entity>) :
        RecyclerView.Adapter<ListRecyclerAdapter.ListViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))
    }

    override fun getItemCount(): Int {
        return if (appList.isNullOrEmpty()) return 0 else appList.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        appList[position]?.run {
            holder.iv_icon.setImageResource(R.drawable.ic_launcher_background)
            holder.iv_name.text = name
            holder.tv_packname.text = "packageName:$versionName"
            holder.itemView.setOnClickListener {
                onItemClickListener?.onItemClick(position)
            }
        }
    }

    class ListViewHolder constructor(val view: View) : RecyclerView.ViewHolder(view) {
        var iv_icon = view.findViewById<ImageView>(R.id.iv_appIcon)
        var iv_name = view.findViewById<TextView>(R.id.tv_appName)
        var tv_packname = view.findViewById<TextView>(R.id.tv_appPackageName)
    }
}