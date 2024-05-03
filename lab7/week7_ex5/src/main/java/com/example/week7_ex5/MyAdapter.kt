package com.example.week7_ex5

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week7_ex5.databinding.ItemMainBinding

class MyViewHolder(val binding: ItemMainBinding): RecyclerView.ViewHolder(binding.root)

class MyAdapter(val datas:MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int = datas.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = MyViewHolder(
        ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int){
        Log.d("kkang", "onBindViewHolder : $position")
        val binding = (holder as MyViewHolder).binding

        binding.itemData.text = datas[position]
        binding.itemRoot.setOnClickListener{
            Log.d("kkang","item root click: $position")
        }
    }
}