package com.example.recyclerviewtest

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewtest.databinding.ItemModelBinding
import com.example.recyclerviewtest.databinding.SquareModelBinding

//class RvAdapter(private val clickListener: IClick) :
class RvAdapter() :
    RecyclerView.Adapter<RvAdapter.MyViewHolder>() {
    private var listener: IClick? = null    //宣告一個IClick型態的變數就可以不用在建構時傳入,彈性較高
    private val innerList = mutableListOf<RvData>()
    var i = 0

    fun setClickListener(listener: IClick) {
        this.listener = listener
    }

    inner class MyViewHolder(itemView: View, private var viewType: Int) :
        RecyclerView.ViewHolder(itemView) {
        private val binding = ItemModelBinding.bind(itemView)
        private val squareBinding = SquareModelBinding.bind(itemView)

        init {
            when (viewType) {
                1 -> {
                    squareBinding.cardView.setOnClickListener {
                        val position = adapterPosition
                        if (position != RecyclerView.NO_POSITION) {
//                    clickListener.onItemClick(position)
                            listener?.onItemClick(position)
                        }
                    }
                    squareBinding.tvItemTitle.setOnClickListener {
                        val position = adapterPosition
                        if (position != RecyclerView.NO_POSITION) {
//                    clickListener.onItemViewClick(innerList[position])
                            listener?.onItemViewClick(innerList[position])
                        }
                    }
                }
                else -> {
                    binding.cardView.setOnClickListener {
                        val position = adapterPosition
                        if (position != RecyclerView.NO_POSITION) {
//                    clickListener.onItemClick(position)
                            listener?.onItemClick(position)
                        }
                    }
                    binding.tvItemTitle.setOnClickListener {
                        val position = adapterPosition
                        if (position != RecyclerView.NO_POSITION) {
//                    clickListener.onItemViewClick(innerList[position])
                            listener?.onItemViewClick(innerList[position])
                        }
                    }
                }
            }
        }

        fun bind(data: RvData) {
            when (viewType) {
                1 -> squareBinding.tvItemTitle.text = data.title
                else -> binding.tvItemTitle.text = data.title
            }
        }
    }

    interface IClick {
        fun onItemClick(position: Int)
        fun onItemViewClick(title: RvData)
    }

    override fun getItemViewType(position: Int): Int {
        return if (innerList[position].isSquare) 1 else 2
    }  //如果data的isSquare是true回傳1,false回傳2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return when (viewType) {
            1 -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.square_model, parent, false)
                i++
                MyViewHolder(view, viewType)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_model, parent, false)
                i++
                MyViewHolder(view, viewType)
            }
        }
    }

    override fun getItemCount(): Int {
        return innerList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val currentItem = innerList[position]

        Log.d("viewHolder counts", "$i")
        Log.d("viewHolder", "$holder")  //看有多少個viewHolder

        //        holder.itemTitle.text = currentItem   //也可改用ViewHolder內定義一個fun bind資料
        holder.bind(innerList[position])
    }

    fun update(updateList: List<RvData>) {
        innerList.clear()
        innerList.addAll(updateList)
        this.notifyDataSetChanged()
    }
}

