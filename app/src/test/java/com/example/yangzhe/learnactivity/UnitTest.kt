package com.example.yangzhe.learnactivity

import org.junit.Assert.assertEquals
import org.junit.Test

class UnitTest {
    @Test
    fun testGeneric() {
        // in 相当于Java中的 ?super 通配符
        val inList: ArrayList<in Number> = ArrayList()
        inList.add(10086)
        inList.add(-1.0f)
        assertEquals(10086, inList[0])
        assertEquals(-1.0f, inList[1])

        // out 相当于Java中的 ?extends 通配符
        val outList: ArrayList<out Number> = ArrayList<Number>().apply {
            add(1000)
            add(2.0f)
        }
        assertEquals(1000, outList[0])
        assertEquals(2.0f, outList[1])
    }
}