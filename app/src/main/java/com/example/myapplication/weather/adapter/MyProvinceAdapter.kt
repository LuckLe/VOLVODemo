package com.example.myapplication.weather.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.myapplication.databinding.LayoutProvinceItemBinding
import com.example.myapplication.weather.bean.city.Provice

/**
 * Created by hml on 2023/4/6.
 */
class MyProvinceAdapter(private val context:Context, private val list:MutableList<Provice>) : Adapter<MyProvinceViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyProvinceViewHolder {
        val binding = LayoutProvinceItemBinding.inflate(LayoutInflater.from(context))
        return MyProvinceViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyProvinceViewHolder, position: Int) {
        holder.mBinding.item = list[position]
        holder.mBinding.recyclerView.layoutManager = GridLayoutManager(context,5)
        holder.mBinding.recyclerView.adapter = MyCitySelectAdapter(context,list[position].city)
        holder.mBinding.executePendingBindings()
    }
}

class MyProvinceViewHolder(val mBinding: LayoutProvinceItemBinding): RecyclerView.ViewHolder(mBinding.root)