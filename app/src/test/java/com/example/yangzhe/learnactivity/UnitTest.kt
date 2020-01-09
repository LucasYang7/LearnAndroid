package com.example.yangzhe.learnactivity

import org.junit.Assert.assertEquals
import org.junit.Test

class UnitTest {
    @Test
    fun testGeneric() {
//        // in 相当于Java中的 ?super 通配符
//        val inList: ArrayList<in Number> = ArrayList()
//        inList.add(10086)
//        inList.add(-1.0f)
//        assertEquals(10086, inList[0])
//        assertEquals(-1.0f, inList[1])
//
//        // out 相当于Java中的 ?extends 通配符
//        val outList: ArrayList<out Number> = ArrayList<Number>().apply {
//            add(1000)
//            add(2.0f)
//        }
//        assertEquals(1000, outList[0])
//        assertEquals(2.0f, outList[1])


        // out 相当于Java中的 ?extends 通配符 站在自身的角度来看，支持get上界类型数据，无法add数据，属于生产者
        val outList: ArrayList<out Number> = ArrayList<Int>().apply {
            add(10086)
        }
        val number: Number = outList[0]  // outList存储的是Number或者Number的子类对象，所以从outList中取出的值可以将其赋值给Number
        // 下面两条语句会报编译错误，Number是Int的父类，
        // 而outList存储的是Number或者Number的子类对象[这是一个范围，没有具体类型],
        // 所以无法插入Number,Int这些具体的类型数据
//        outList.add(number)
//        outList.add(10000)
        assertEquals(10086, number)

        // in 相当于Java中的 ?super 通配符  站在自身的角度来看，支持add数据到自身，无法提供get具体类型数据给外面，属于消费者
        val inList: ArrayList<in Int> = ArrayList<Number>().apply {
            add(2020.1f)
        }
//        val int: Int = inList[0]     // 这条语句会报编译错误，in关键字修饰的inList取出的元素是Any?对象，无法向下转型为Int对象
        val any: Any? = inList[0]    // in关键字修饰的inList取出的元素是Any?对象
        assertEquals(2020.1f, any)
        inList.add(2020)     // in关键字支持add操作
        assertEquals(2020, inList[1])
    }
}