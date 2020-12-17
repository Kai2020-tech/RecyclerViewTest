package com.example.recyclerviewtest

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewtest.databinding.ItemModelBinding

class RvAdapter() :
    RecyclerView.Adapter<RvAdapter.MyViewHolder>() {
    private val innerList = mutableListOf<String>()
    private var i = 0

    inner class MyViewHolder(itemView: ItemModelBinding) : RecyclerView.ViewHolder(itemView.root) {
        var itemTitle = itemView.tvItemTitle

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemModelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        i++
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return innerList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = innerList[position]
        holder.itemTitle.text = currentItem
        Log.d("viewHolder counts", "$i")
        Log.d("viewHolder", "$holder")
    }

    fun update(updateList: List<String>) {
        innerList.clear()
        innerList.addAll(updateList)
        this.notifyDataSetChanged()
    }
}
