package com.example.recyclerviewtest

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewtest.databinding.ItemModelBinding

class RvAdapter(private val clickListener: IClick) :
    RecyclerView.Adapter<RvAdapter.MyViewHolder>() {
    private var listener: IClick? = null    //宣告一個IClick型態的變數就可以不用在建構時傳入,彈性較高
    private val innerList = mutableListOf<String>()
    var i = 0

    fun setClickListener(listener: IClick){
        this.listener = listener
    }

    inner class MyViewHolder(itemView: ItemModelBinding) : RecyclerView.ViewHolder(itemView.root) {
        private var itemTitle = itemView.tvItemTitle

        init {
            itemView.cardView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    clickListener.onItemClick(position)
                    listener?.onItemClick(position)
                }
            }
            itemView.tvItemTitle.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    clickListener.onItemViewClick(innerList[position])
                }
            }
        }

        fun bind(data: String){
            itemTitle.text = data
        }
    }

    interface IClick {
        fun onItemClick(position: Int)
        fun onItemViewClick(title: String)
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
//        val currentItem = innerList[position]
//        holder.itemTitle.text = currentItem   //也可改用ViewHolder內定義一個fun bind資料
        Log.d("viewHolder counts", "$i")
        Log.d("viewHolder", "$holder")

        holder.bind(innerList[position])
    }

    fun update(updateList: List<String>) {
        innerList.clear()
        innerList.addAll(updateList)
        this.notifyDataSetChanged()
    }
}

