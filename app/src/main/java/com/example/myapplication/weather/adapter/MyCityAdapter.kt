package com.example.myapplication.weather.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.LayoutItem2Binding
import com.example.myapplication.weather.bean.CastItem

/**
 * Created by hml on 2023/4/6.
 */
class MyCityAdapter( private val context:Context, private val list:MutableList<CastItem>) : RecyclerView.Adapter<MyCityViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCityViewHolder {
        val binding = LayoutItem2Binding.inflate(LayoutInflater.from(context))
        return MyCityViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyCityViewHolder, position: Int) {
        holder.mBinding.item = list[position]
        holder.mBinding.executePendingBindings()
    }
}

class MyCityViewHolder(val mBinding: LayoutItem2Binding): RecyclerView.ViewHolder(mBinding.root)