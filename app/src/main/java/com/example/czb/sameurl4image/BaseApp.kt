package com.example.czb.sameurl4image

import android.app.Application

/**
 * Created by czb on 2018/11/20.
 */
class BaseApp : Application() {
    companion object {
        private lateinit var INSTANCE: Application

        @JvmStatic
        fun getInstance() = INSTANCE
    }
    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}