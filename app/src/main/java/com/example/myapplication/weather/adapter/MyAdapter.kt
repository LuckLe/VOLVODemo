package com.example.myapplication.weather.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.LayoutItemBinding
import com.example.myapplication.weather.CityWeatherActivity
import com.example.myapplication.weather.bean.Forecast
import com.example.myapplication.weather.bean.city.City

/**
 * Created by hml on 2023/4/6.
 */
class MyAdapter( private val context:Context, private val list:MutableList<Forecast>) : RecyclerView.Adapter<MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = LayoutItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.mBinding.item = list[position]
        holder.mBinding.click = object : OnItemClick{
            override fun click(item: Forecast) {
                context.startActivity(Intent(context, CityWeatherActivity::class.java).apply {
                    putExtras(Bundle().apply {
                        putSerializable("city",City(item.adcode!!,item.city!!))
                    })
                })
            }
        }
        holder.mBinding.executePendingBindings()
    }
}

class MyViewHolder(val mBinding: LayoutItemBinding): RecyclerView.ViewHolder(mBinding.root)

interface OnItemClick{
    fun click(item:Forecast)
}