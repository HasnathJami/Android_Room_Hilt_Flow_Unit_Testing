package com.jsn.android_room_hilt_flow_unit_testing

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()
    }
}