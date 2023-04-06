package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.weather.CitySelectActivity
import com.example.myapplication.weather.ListActivity

/**
 * Created by hml on 2023/4/6.
 */
class MainActivity : AppCompatActivity() {

    private val TAG = this::class.java.simpleName
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btn1.setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
        }
        mBinding.btn2.setOnClickListener {
            startActivity(Intent(this, CitySelectActivity::class.java))
        }

    }
}