package com.example.myapplication.weather

import android.app.Application
import androidx.multidex.MultiDex
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by hml on 2023/4/6.
 */

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
    }
}