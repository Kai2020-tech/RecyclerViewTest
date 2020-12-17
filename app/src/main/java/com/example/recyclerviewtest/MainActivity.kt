package com.example.recyclerviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),IClick {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvTitle.text = "Recycler View Test"

        val dataList = listOf<String>(
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"
        )

        val rvAdapter = RvAdapter(this)
        binding.rvRecyclerView.adapter = rvAdapter
        binding.rvRecyclerView.layoutManager = LinearLayoutManager(this)
        rvAdapter.update(dataList)
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "position $position clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onItemViewClick(title: String) {
        Toast.makeText(this, " $title clicked", Toast.LENGTH_SHORT).show()
    }
}