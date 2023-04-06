package com.example.myapplication.weather.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.LayoutCityItemBinding
import com.example.myapplication.weather.CityWeatherActivity
import com.example.myapplication.weather.bean.city.City

/**
 * Created by hml on 2023/4/6.
 */
class MyCitySelectAdapter( private val context:Context, private val list:MutableList<City>) : RecyclerView.Adapter<MyCitySelectViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCitySelectViewHolder {
        val binding = LayoutCityItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyCitySelectViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyCitySelectViewHolder, position: Int) {
        holder.mBinding.item = list[position]
        holder.mBinding.click = object : OnCityClick {
            override fun click(city: City) {
                context.startActivity(Intent(context, CityWeatherActivity::class.java).apply {
                    putExtras(Bundle().apply {
                        putSerializable("city",city)
                    })
                })
            }
        }
        holder.mBinding.executePendingBindings()
    }
}

class MyCitySelectViewHolder(val mBinding: LayoutCityItemBinding): RecyclerView.ViewHolder(mBinding.root)

interface OnCityClick{
    fun click(city: City)
}