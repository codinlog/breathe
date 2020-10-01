package com.codinlog.breathe.application

import android.app.Application
import android.content.Context

class Application : Application() {
    companion object {
        lateinit var mContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        mContext = this
    }
}