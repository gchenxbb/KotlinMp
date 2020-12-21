package com.kotlin.app.demo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.app.R

//https://www.runoob.com/kotlin/kotlin-tutorial.html
class KlActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kl)
        prin("hello kotlin!");

        var age: String? = null;
        //val ages = age!!.toInt();//抛出空异常。
        var age2 = age?.toInt();//不作处理，返回null
        val age3 = age?.toInt() ?: -1;


        print(age3);

        Log.d("MainActivityW", "age3 is $age2 !");
        var len: Int? = getStringLength("fd");
        Log.d("MainActivityW", "len is $len !");
        for (i in 1..4) println(i);

        var persion: Persion = Persion("chen", 200, "3711502304304300959");
        println("name:${persion.name}")
        persion.name = "wang";
        println("namemodify:${persion.name}")

        persion.weight = 9;
        println("weight:${persion.weight}")
        persion.weight = 20;
        println("weightmodify:${persion.weight}")

        val s = Student("chenx", 29, "2019003", 62);
        println("name:${s.name}")
        println("age:${s.age}")
        println("no:${s.no}")
        println("scole:${s.scole}")
    }

    override fun onResume() {
        super.onResume()
    }

    fun getStringLength(obj: Any): Int? {
        if (obj is String) {
            return obj.length;
        }
        return null;
    }

    fun test(a: Int, b: Int): Int {
        return a + b;
    }

    fun sum(a: Int, b: Int) = a + b;
    fun sum2(a: Int, b: Int) = a + b;


    //public 方法必须生命返回类型
    public fun sumw(a: Int, b: Int): Int = a + b;

    public fun sump(a: Int, b: Int): Int = a + b;

    fun printSum(a: Int, b: Int): Unit {
        print(a + b);
    }

    public fun printSum2(a: Int, b: Int) {
        print(a + b);
    }

    //可变参数
    fun vars(vararg v: Int) {
        for (vt in v) {
            print(vt);
        }
    }

    fun main(args: Array<String>) {
        vars(1, 2, 3, 4, 5);
    }

    fun main2(args: Array<String>) {
        val sumLambda: (Int, Int) -> Int = { x, y -> x + y }
        println(sumLambda(1, 2));
    }

    var a: Int = 1;
    var b = 1;

    var s1 = "a is $a"

    var s3 = "${s1.replace("is", "was")},but now is $a";

    fun prin(s: String) {
        println(s3);
        println(s);
    }

    fun parseInt(str: String): Int? {
        var st = str?.toInt() ?: -1;
        return st;
    }

    fun main3(args: Array<String>) {
        if (args.size < 2) {
            print("two integers expected");
            return;
        }
        var x = parseInt(args[0]);
        var y = parseInt(args[1]);
        if (x != null && y != null) {
            print(x * y);
        }

    }
}
