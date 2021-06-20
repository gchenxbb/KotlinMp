package com.kotl.jetpack.kotlindemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.kotl.jetpack.R

/**
 * suspend挂起函数，在协程或其他挂起函数使用。
 */
class KlActivity : AppCompatActivity() {
    var tag: String = "KotlinX"
    var robbo: Robbo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kl)
        Log.d(tag, "calling onCreate start..")

        findViewById<TextView>(R.id.tv_coroutine).setOnClickListener {
            startActivity(Intent(this, KlCoroutineActivity::class.java))
        }
        /**
         * let with，run apply
         */
        robbo = Robbo()
        //let
        let(robbo)
        //with
        with(robbo!!)
        //run
        run(robbo)
        //apply
        apply(robbo)

        var setv = hashSetOf(1, 7, 20, 34)
        var listv = arrayListOf(1, 2, 23, 45)
        val mapv = hashMapOf(1 to "one", 8 to "eight", 10 to "ten")

        Log.d(tag, "setv result:$setv,javaclass:${setv.javaClass}")
        Log.d(tag, "listv result:$listv,javaclass:${listv.javaClass}")
        Log.d(tag, "mapv result:$mapv,javaclass:${mapv.javaClass}")
        Log.d(tag, "setv result:${setv.max()}")
        Log.d(tag, "listv result:${listv[3]}")
        Log.d(tag, "mapv result:${mapv[8]}")

        val strs = listOf("first", "second", "third")
        val numbers = setOf(12, 32, 3)
        Log.d(tag, "strs result:${strs.last()}")
        Log.d(tag, "numbers result:${numbers.max()}")

        val url = "https://www.baidu.com"
        performRequest(url, { code, content ->
            Log.d(tag, "code:$code, content:$content")
        })

        prin("hello kotlin!");
        var age: String? = null;
        //val ages = age!!.toInt();//抛出空异常。
        var age2 = age?.toInt();//不作处理，返回null
        val age3 = age?.toInt() ?: -1;


        Log.d(tag, "age3:$age3");

        Log.d("MainActivityW", "age3 is $age2 !");
        var len: Int? = getStringLength("fd");
        Log.d("MainActivityW", "len is $len !");
        for (i in 1..4) Log.d(tag, "i:$i");

        var persion: Persion = Persion("chen", 200, "3711502304304300959");
        Log.d(tag, "name:${persion.name}")
        persion.name = "wang";
        Log.d(tag, "namemodify:${persion.name}")

        persion.weight = 9;
        Log.d(tag, "weight:${persion.weight}")
        persion.weight = 20;
        Log.d(tag, "weightmodify:${persion.weight}")

        val s = Student("chenx", 29, "2019003", 62);
        Log.d(tag, "name:${s.name}")
        Log.d(tag, "age:${s.age}")
        Log.d(tag, "no:${s.no}")
        Log.d(tag, "scole:${s.scole}")
    }


    fun performRequest(url: String, callback: (code: Int, content: String) -> Unit) {
        callback(200, "success")
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
        Log.d(tag, "${a + b}");
    }

    public fun printSum2(a: Int, b: Int) {
        Log.d(tag, "${a + b}");
    }

    //可变参数
    fun vars(vararg v: Int) {
        for (vt in v) {
            Log.d(tag, "vt:$vt");
        }
    }

    fun main(args: Array<String>) {
        vars(1, 2, 3, 4, 5);
    }

    fun main2(args: Array<String>) {
        val sumLambda: (Int, Int) -> Int = { x, y -> x + y }
        Log.d(tag, "${sumLambda(1, 2)}");
    }

    var a: Int = 1;
    var b = 1;

    var s1 = "a is $a"

    var s3 = "${s1.replace("is", "was")},but now is $a";

    fun prin(s: String) {
        Log.d(tag, s3);
        Log.d(tag, s);
    }

    fun parseInt(str: String): Int? {
        var st = str?.toInt() ?: -1;
        return st;
    }

    fun main3(args: Array<String>) {
        if (args.size < 2) {
            Log.d(tag, "two integers expected");
            return;
        }
        var x = parseInt(args[0]);
        var y = parseInt(args[1]);
        if (x != null && y != null) {
            Log.d(tag, "${x * y}");
        }

    }


    fun let(robbo: Robbo?) {
        val lo = robbo?.let {
            Log.d(tag, it.name)
            Log.d(tag, it.city)
            Log.d(tag, it.url)
            1000
        }
        Log.d(tag, "let result:$lo")
//        Log.d(tag,"let result:$lo")
    }

    fun with(robbo: Robbo) {
        val lo = with(robbo) {
            Log.d(tag, name)
            Log.d(tag, city)
            Log.d(tag, url)
            1000
        }
//        Log.d(tag,"with result:$lo")
        Log.d(tag, "with result:$lo")
    }


    fun run(robbo: Robbo?) {
        val lo = robbo?.run {
            Log.d(tag, name)
            Log.d(tag, city)
            Log.d(tag, url)
            1000
        }
        Log.d(tag, "run result:$lo")
//        Log.d(tag,)
    }

    fun apply(robbo: Robbo?) {
        var lo = robbo?.apply {
            Log.d(tag, name)
            Log.d(tag, city)
            Log.d(tag, url)
            1000
        }
        Log.d(tag, "apply result:$lo")

//        Log.d(tag,"apply result:$lo")
    }
}
