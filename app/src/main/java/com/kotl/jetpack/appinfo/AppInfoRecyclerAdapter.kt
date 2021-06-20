package com.kotl.jetpack.appinfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotl.jetpack.R
import com.kotl.jetpack.data.ApplicationLocal

class AppInfoRecyclerAdapter(private val appList: MutableList<ApplicationLocal>) :
        RecyclerView.Adapter<AppInfoRecyclerAdapter.AppViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun getItemCount(): Int {
        return if (appList.isNullOrEmpty()) 0 else appList.size
    }

    override fun onBindViewHolder(viewHolder: AppViewHolder, position: Int) {
        appList[position]?.run {
            viewHolder.iv_appIcon.setImageDrawable(appList[position].icon)
            viewHolder.tv_appName.text = appList[position].name
            viewHolder.tv_appPackageName.text = appList[position].packageName
            viewHolder.itemView.setOnClickListener {
                onItemClickListener?.onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): AppViewHolder {
        return AppViewHolder(
                LayoutInflater.from(viewGroup.context).inflate(R.layout.item_applist, viewGroup, false)
        )
    }

    class AppViewHolder constructor(val view: View) : RecyclerView.ViewHolder(view) {
        val iv_appIcon = view.findViewById<ImageView>(R.id.iv_appIcon)
        val tv_appName = view.findViewById<TextView>(R.id.tv_appName)
        val tv_appPackageName = view.findViewById<TextView>(R.id.tv_appPackageName)
    }

}