package com.jsn.android_room_hilt_flow_unit_testing

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class FlowDemoTest {

    @Before
    fun setUp() {
    }

    @Test
    fun getFlowTest() = runTest {
        val sut = FlowDemo()
        var result = sut.getFlow().toList()
        var result2 = sut.getFlow().first()
        Assert.assertEquals(listOf(1,2), result)
        Assert.assertEquals(1, result2)
    }

    @After
    fun tearDown() {
    }
}