package com.example.myapplication.weather

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityCityBinding
import com.example.myapplication.utils.AssetsUtil
import com.example.myapplication.utils.GsonUtil
import com.example.myapplication.weather.adapter.MyProvinceAdapter
import com.example.myapplication.weather.bean.city.ListProvice
import com.example.myapplication.weather.bean.city.Provice

/**
 * Created by hml on 2023/4/6.
 */
class CitySelectActivity : AppCompatActivity() {

    private val TAG = this::class.java.simpleName
    private lateinit var mBinding: ActivityCityBinding
    lateinit var dialog : ProgressDialog;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityCityBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        dialog = ProgressDialog(this)
        mBinding.imgvBack.setOnClickListener { finish() }

        request()

    }

    private fun request() {
        val list = getCity()
        mBinding.recyclerView.layoutManager = LinearLayoutManager(this@CitySelectActivity)
        mBinding.recyclerView.adapter = MyProvinceAdapter(this@CitySelectActivity,list)
    }

    private fun getCity(): MutableList<Provice> {
        val jsonData = AssetsUtil.loadJSONFromAsset(this, "city_list.json")
        var list : MutableList<Provice> = mutableListOf()
        jsonData?.let{ it ->
            val doctorList = GsonUtil.convert2JsonObject(it, ListProvice::class.java)
            doctorList?.let {
                list = it
            }
        }
        return list
    }
}