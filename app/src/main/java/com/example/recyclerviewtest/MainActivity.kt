package com.example.recyclerviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), RvAdapter.IClick {
    private lateinit var actManBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actManBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(actManBinding.root)
//        val a = binding.root
//        val b = R.layout.activity_main

        actManBinding.tvTitle.text = "Recycler View Test"

        val dataList = listOf<RvData>(
            RvData("1"),
            RvData("2"),
            RvData("3"),
            RvData("4"),
            RvData("5", true),
            RvData("6"),
            RvData("7", true),
            RvData("8"),
            RvData("9"),
            RvData("10")
        )

        val rvAdapter = RvAdapter().apply {
            setClickListener(this@MainActivity)
        }//要用到點擊時才傳入的第一種方式
        //上面apply等於這樣的寫法 rvAdapter.setClickListener(this)

//        rvAdapter.setClickListener(object : RvAdapter.IClick{
//            override fun onItemClick(position: Int) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onItemViewClick(title: String) {
//                TODO("Not yet implemented")
//            }
//        })  //第二種方式


        actManBinding.rvRecyclerView.adapter = rvAdapter
        actManBinding.rvRecyclerView.layoutManager = LinearLayoutManager(this)
        rvAdapter.update(dataList)
    }

    override fun onItemClick(position: Int): Unit {
        Toast.makeText(this, "position $position clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onItemViewClick(rvData: RvData) {
        Toast.makeText(this, " ${rvData.title} clicked", Toast.LENGTH_SHORT).show()
    }
}