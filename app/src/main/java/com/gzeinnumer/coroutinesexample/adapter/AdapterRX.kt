package com.gzeinnumer.coroutinesexample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gzeinnumer.coroutinesexample.R
import com.gzeinnumer.coroutinesexample.model.ArticlesItem

class AdapterRX(private val contex: Context, private val list: List<ArticlesItem>?) : RecyclerView.Adapter<AdapterRX.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view: View = LayoutInflater.from(contex).inflate(R.layout.item, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.tvTitle.text = list?.get(position)?.title
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.tv_title)
    }

}
