package com.example.myapplication.weather

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityLiveDataBinding
import com.example.myapplication.utils.DataUtils
import com.example.myapplication.weather.adapter.MyAdapter
import com.example.myapplication.weather.api.ApiService
import com.example.myapplication.weather.bean.Forecast
import com.example.myapplication.weather.bean.WeatherBaseBean
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
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
    //    北京市
//    上海市
//    广州市
//    深圳市
//    苏州市
//    沈阳市
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
            Log.i(TAG,"开始时间2：${DataUtils.stampToDate("${System.currentTimeMillis()}")}, name = ${Thread.currentThread().name}")

//            这种同步调用6次接口，总的请求时间为6次之和，不好
//            loadDataSync()

//            这种异步调用6次接口，总的请求时间为6次中最长的一次，好
            loadDataAsync(this)

            Log.i(TAG,"结束时间2：${DataUtils.stampToDate("${System.currentTimeMillis()}")}")
            mBinding.recyclerView.layoutManager = LinearLayoutManager(this@ListActivity)
            mBinding.recyclerView.adapter = MyAdapter(this@ListActivity,list)
            hindLoading()
        }
    }

    /**
     * 这种异步调用6次接口，总的请求时间为6次中最长的一次，好
     */
    private suspend fun loadDataAsync(scope: CoroutineScope) {
        listCityCodes.map {
            scope.async {
                loadData(it)
            }
        }.forEach {
            it.await().forecasts?.get(0)?.let {forecast->
                list.add(forecast)
                Log.i(TAG,"完成一个数据了：${forecast.city},  name = ${Thread.currentThread().name}")
            }
        }
    }

    /***
     * 这种同步调用6次接口，总的请求时间为6次之和，不好
     */
    private suspend fun loadDataSync() {
        var weatherBaseBean : WeatherBaseBean? = null
        listCityCodes.forEach { it ->
            weatherBaseBean = loadData(it)
            weatherBaseBean?.forecasts?.get(0)?.let { item ->
                Log.i(TAG,"完成一个数据了：${item.city}")
                list.add(item)
            }
        }
    }

    private suspend fun loadData(cityCode:String): WeatherBaseBean {
        delay(1000)
        return retrofit.create(ApiService::class.java).getForecastWeather(cityCode)
    }

    private fun showLoading() {
        dialog.show()
    }

    private fun hindLoading(){
        dialog.dismiss()
    }
}