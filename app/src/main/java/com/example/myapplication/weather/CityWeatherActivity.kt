package com.example.myapplication.weather

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityCityWheatherBinding
import com.example.myapplication.weather.adapter.MyCityAdapter
import com.example.myapplication.weather.api.ApiService
import com.example.myapplication.weather.bean.CastItem
import com.example.myapplication.weather.bean.Forecast
import com.example.myapplication.weather.bean.WeatherBaseBean
import com.example.myapplication.weather.bean.city.City
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by hml on 2023/4/6.
 */
@AndroidEntryPoint
class CityWeatherActivity : AppCompatActivity() {

    private val TAG = this::class.java.simpleName
    private lateinit var mBinding: ActivityCityWheatherBinding
    private var list : MutableList<Forecast> = mutableListOf()

    @Inject
    lateinit var retrofit: Retrofit

    lateinit var dialog : ProgressDialog;

    lateinit var city: City

    private var stateFlow = MutableStateFlow<MutableList<CastItem>>(mutableListOf())
    private lateinit var myAdapter : MyCityAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityCityWheatherBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        getIntentData()

        mBinding.tvTitle.text = city.name

        mBinding.recyclerView.layoutManager = LinearLayoutManager(this@CityWeatherActivity)
        myAdapter = MyCityAdapter(this@CityWeatherActivity)
        mBinding.recyclerView.adapter = myAdapter

        dialog = ProgressDialog(this)

        mBinding.imgvBack.setOnClickListener { finish() }

        collectDate()

        request()

    }

    private fun collectDate() {
        lifecycleScope.launch {
            stateFlow.collect {
                myAdapter.setData(it)
            }
        }
    }

    private fun getIntentData() {
        val bean = intent.extras?.getSerializable("city")
        if (bean is City){
            city = bean
            Log.i(TAG,"CITY = $city")
        }else{
            finish()
        }
    }

    private fun request() {
        showLoading()
        lifecycleScope.launch {
            var weatherBaseBean : WeatherBaseBean? = null
            weatherBaseBean = retrofit.create(ApiService::class.java).getForecastWeather(city.adcode)
            weatherBaseBean.forecasts?.get(0)?.casts?.let { list ->
                stateFlow.value = list
            }
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