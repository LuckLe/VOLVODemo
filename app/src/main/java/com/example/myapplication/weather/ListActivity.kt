package com.example.myapplication.weather

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityLiveDataBinding
import com.example.myapplication.weather.adapter.MyAdapter
import com.example.myapplication.weather.api.ApiService
import com.example.myapplication.weather.bean.Forecast
import com.example.myapplication.weather.bean.WeatherBaseBean
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by hml on 2023/4/6.
 */
@AndroidEntryPoint
class ListActivity : AppCompatActivity() {

    private val TAG = this::class.java.simpleName
    private lateinit var mBinding: ActivityLiveDataBinding
    private val listCityCodes = mutableListOf("110000","310000","440100","440300","320500","210100")
    private var list : MutableList<Forecast> = mutableListOf()

    @Inject
    lateinit var retrofit: Retrofit

    lateinit var dialog : ProgressDialog;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityLiveDataBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        dialog = ProgressDialog(this)

        mBinding.imgvBack.setOnClickListener { finish() }

        request()

    }

    private fun request() {
        showLoading()
        lifecycleScope.launch {
            var weatherBaseBean : WeatherBaseBean? = null
            listCityCodes.forEach { it ->
                weatherBaseBean = retrofit.create(ApiService::class.java).getForecastWeather(it)
                weatherBaseBean?.forecasts?.get(0)?.let { item ->
                    list.add(item)
                }
            }
            mBinding.recyclerView.layoutManager = LinearLayoutManager(this@ListActivity)
            mBinding.recyclerView.adapter = MyAdapter(this@ListActivity,list)
            hindLoading()
        }
    }

    private fun showLoading() {
        dialog.show()
    }

    private fun hindLoading(){
        dialog.dismiss()
    }
}