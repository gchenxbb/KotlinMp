package com.kotl.jetpack.kotlindemo

import android.util.Log

open class Persion(name: String, age: Int) {
    var tag: String = "KotlinX"

    constructor(name: String, age: Int, certId: String) : this(name, age) {
        Log.d(tag, "certId is $certId")
    }

    init {
        Log.d(tag, "name is $name")
        Log.d(tag, "age is $age")
    }

    var name: String = name
        get() = field.toUpperCase()
        set
    var age: Int = age
        get() = field
        set
    var weight: Int = 100
        get() = field
        set(value) {
            if (value < 10) {
                field = value
            } else {
                field = -1
            }
        }
    var height: Float = 165.5f
        private set;


}


class Student(name: String, age: Int, no: String, scole: Int) : Persion(name, age) {

    var no: String = no
    var scole: Int = scole
}