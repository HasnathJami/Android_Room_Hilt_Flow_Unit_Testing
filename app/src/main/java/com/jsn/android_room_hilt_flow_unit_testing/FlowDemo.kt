package com.jsn.android_room_hilt_flow_unit_testing

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class FlowDemo {
    fun getFlow() = flow<Int>{
        emit(1)
        delay(2000)
        emit(2)
        delay(2000)
    }
}