package com.kotl.jetpack.kotlindemo

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.kotl.jetpack.R
import kotlinx.android.synthetic.main.activity_coroutine.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * suspend挂起函数，在协程或其他挂起函数使用。
 */
class KlCoroutineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)
        println("calling onCreate start..")

        findViewById<Button>(R.id.btn_coroutine1).setOnClickListener { coroutine1() }
        findViewById<Button>(R.id.btn_coroutine2).setOnClickListener { coroutine2() }
        findViewById<Button>(R.id.btn_coroutine3).setOnClickListener { coroutine3() }
        findViewById<Button>(R.id.btn_coroutine4).setOnClickListener { coroutine4() }
        findViewById<Button>(R.id.btn_coroutine5).setOnClickListener { coroutine5() }
        findViewById<Button>(R.id.btn_coroutine6).setOnClickListener { coroutine6() }
        findViewById<Button>(R.id.btn_coroutine7).setOnClickListener { coroutine7() }
        findViewById<Button>(R.id.btn_coroutine8).setOnClickListener { coroutine8() }
        findViewById<Button>(R.id.btn_coroutine9).setOnClickListener { coroutine9() }
        findViewById<Button>(R.id.btn_coroutine10).setOnClickListener { coroutine10() }
        findViewById<Button>(R.id.btn_coroutine11).setOnClickListener { coroutine11() }
        findViewById<Button>(R.id.btn_coroutine12).setOnClickListener { coroutine12() }
        findViewById<Button>(R.id.btn_coroutine13).setOnClickListener { coroutine13() }


    }

    //runBlocking 阻塞当前线程,协程遇到suspend delay时挂起
    //CoroutineScope.launch不阻塞当前线程
    fun coroutine1() {
        println("runBlocking start..")
        runBlocking {
            println("runBlocking in ..")
            delay(100)//挂起后，线程阻塞
            launch { // 在 runBlocking 作用域中启动一个新协程，不会阻塞runBlocking，先顺序执行runBlocking后面方法
                println("runBlocking launch 1")
                delay(300)
                println("runBlocking launch 2")
            }
            println("runBlocking berofe delay ..")
            suspendFoo()//遇到suspend，回到launch{}
            println("runBlocking delay1 end..")
            println("runBlocking delay2 end..")
            println("runBlocking delay3 end..")
            println("runBlocking delay4 end..")
            println("runBlocking delay5 end..")
            println("runBlocking delay6 end..")
        }
        println("runBlocking end..")
    }

    //runBlocking 内部launch{}和coroutineScope{}
    fun coroutine2() {
        println("runBlocking start..")
        runBlocking {
            println("runBlocking in ..")
            delay(1000)
            launch {
                println("launch 0")
            }
            coroutineScope {//协程作用域，直接进入 执行，因为是suspend，该{}整体结束后，才执行后面方法。
                println("coroutineScope  1")//先执行，再回去launch 0
                delay(1000)
                println("coroutineScope  2")
            }
            println("runBlocking delay0 end..")
            println("runBlocking delay1 end..")
            println("runBlocking delay2 end..")
            println("runBlocking delay3 end..")
            println("runBlocking delay4 end..")
        }

        println("runBlocking end..")
    }

    //CoroutineScope
    fun coroutine3() {
        println("calling CoroutineScope start..")
        CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate).launch {
            println("calling CoroutineScope in ..")
            var value1 = suspendfakeResult1()//挂起函数，遇到时先挂起释放主线程。
            println("print value1L:$value1")
            var value2 = suspendfakeResult2()
            println("print value1L:$value2")
            var value3 = suspendfakeResult3(value1, value2)
            btn_coroutine3.text = "CoroutineScope.launch $value3"
            println("calling CoroutineScope delay end.$value3")
        }
        println("calling CoroutineScope end..")
    }

    //GlobalScope.launch
    fun coroutine4() {
        println("calling GlobalScope start..")
        //默认在后台新的调度线程中
        GlobalScope.launch {
            println("calling GlobalScope in ..")
            println("calling GlobalScope thread is :${Thread.currentThread().name}")
            delay(2000)
            println("calling GlobalScope delay end..")
        }

        //runBlocking执行完成才向下执行
        runBlocking {
            println("calling runBlocking in ..")
            println("calling runBlocking thread is :${Thread.currentThread().name}")
            delay(10000)
            println("calling runBlocking delay end..")
        }
        println("calling GlobalScope1 end..")
        println("calling GlobalScope2 end..")
        println("calling GlobalScope3 end..")
    }

    //withContext
    fun coroutine5() {
        println("calling GlobalScope start..")
        CoroutineScope(Dispatchers.Main).launch {
            val time1 = System.currentTimeMillis()
            val task1 = withContext(Dispatchers.IO) {
                delay(3000)
                println("执行task1.... [当前线程为：${Thread.currentThread().name}]")
                "one"  //返回结果赋值给task1
            }//withContext 是挂起函数，所以，任务串行执行

            val task2 = withContext(Dispatchers.IO) {
                delay(10000)
                println("执行task2... [当前线程为：${Thread.currentThread().name}]")
                "two"  //返回结果赋值给task2
            }
            println("task1 = $task1  , task2 = $task2 , " +
                    "耗时 ${System.currentTimeMillis() - time1} ms  [当前线程为：${Thread.currentThread().name}]")
        }

        println("calling CoroutineScope end..")
    }

    //async + deferred
    fun coroutine6() {
        println("calling CoroutineScope start..")
        CoroutineScope(Dispatchers.Main).launch {
            val time0 = System.currentTimeMillis()
            val deferred0 = async(Dispatchers.IO) {
                delay(3000)
                println("执行deferred0... [当前线程为：${Thread.currentThread().name}]")
                "zero"  //返回结果赋值给deferred0
            }.await()
            println("deferred0 = $deferred0  , " +
                    "耗时 ${System.currentTimeMillis() - time0} ms  [当前线程为：${Thread.currentThread().name}]")
            val time1 = System.currentTimeMillis()
            val deferred1 = async(Dispatchers.IO) {
                delay(3000)
                println("执行deferred1... [当前线程为：${Thread.currentThread().name}]")
                "one"
            }

            val deferred2 = async(Dispatchers.IO) {
                delay(7000)
                println("执行deferred2.. [当前线程为：${Thread.currentThread().name}]")
                "two"
            }
            println("deferred1 = ${deferred1.await()}  , deferred2 = ${deferred2.await()} , " +
                    "耗时 ${System.currentTimeMillis() - time1} ms  [当前线程为：${Thread.currentThread().name}]")

            btn_coroutine6.text = "async:1"
        }
        println("calling CoroutineScope end..")
    }

    //Flow
    fun coroutine7() {
        println("calling flow start..")
        GlobalScope.launch {
            println("calling flowfoo..")
            val flow = flowfoo()
            println("calling collect..")
            flow.collect { value -> println(value) }//suspend,挂起。
            println("calling collect again..")
            flow.collect { value -> println(value) }
        }
        println("calling flow end..")
    }

    //顺序:1,4,5,8,2,6,3,9,7,10
    fun coroutine8() {
        println("runBlocking start..")
        //阻塞当前线程，内部全部执行完(包括子协程)才可以向下继续
        runBlocking {
            println("T-1")
            delay(300)
            //子协程，阻塞任务
            launch { // 在 runBlocking 作用域中启动一个新协程
                println("T-2")
                delay(100)
                println("T-3")
            }
            println("T-4")
            coroutineScope {//协程作用域
                println("T-5")
                launch {
                    println("T-6")
                    delay(1000)
                    println("T-7")
                }
                println("T-8")
                delay(600)
                println("T-9")
            }
            println("T-10")
        }
        println("runBlocking end..")
    }

    //runBlocking 多个launch{}
    fun coroutine9() {
        println("calling coroutine start...")
        runBlocking {
            println("calling  1...")
            launch {
                println("calling  2...")
                delay(1000)
                println("calling  3...")
            }
            launch {
                println("calling  4...")
                delay(1000)
                println("calling  5...")
            }
            launch {
                println("calling  6...")
                delay(1000)
                println("calling  7...")
            }
            println("calling  8...")
            println("calling coroutine {} end...")
        }
        println("calling  center...")
        runBlocking {
            delay(1500)
        }
        println("calling coroutine end...")
    }

    ///runBlocking内部GlobalScope.launch
    fun coroutine10() {
        runBlocking {
            println("A")
            GlobalScope.launch() {
                println("B")
                delay(1000)
                println("C")
            }
            println("D")
        }
        println("F")
    }

    //job+cancel  取消协程
    fun coroutine11() {
        println("calling start..")
        runBlocking {
            val job = launch {
                repeat(10) {
                    println("hello:$it")
                    delay(500)
                }
            }
            delay(3200)
            println("world..")
            job.cancel()
            println("welcome..")
        }

        println("calling end..")
    }

    //runBlocking io线程
    fun coroutine12() {
        println("calling start :${Thread.currentThread().name}")
        //调用了 runblocking 的线程会阻塞直到内部的协程执行完毕
        //这里是main线程调用了runBlocking，所以阻塞main线程
        runBlocking(Dispatchers.IO) {
            println("hello runBlocking...${Thread.currentThread().name}")
            delay(5000)
            println("delay end...")
        }
        println("calling end :${Thread.currentThread().name}")
    }

    //join，再任务1执行完后再执行任务2
    fun coroutine13() {
        println("calling join start..")
        runBlocking {
            // 启动协程 1
            val job1 = launch {
                delay(3000)
                println("开始执行1:${System.currentTimeMillis()}")
            }
            println("准备等待1执行..")
            //等待协程1结束再向下执行
            job1.join()
            println("开始向下执行..")
            // 启动协程 2
            launch {
                delay(1000)
                println("开始执行2:${System.currentTimeMillis()}")
            }
        }

        println("calling join end..")
    }

    fun flowfoo(): Flow<Int> = flow {
        println("flow started,wait emit..${Thread.currentThread().name}")
        delay(3000)
        for (i in 1..3) {
            emit(i)
        }
    }.flowOn(Dispatchers.IO)

    suspend fun foo(): List<Int> {
        delay(100) //需要suspend
        return listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    }

    suspend fun suspendFoo(): Int {
        delay(1)
        return 1
    }

    suspend fun suspendfakeResult1(): Int {
        delay(1000)
        return 1
    }

    suspend fun suspendfakeResult2(): Int {
        delay(1000)
        return 2
    }

    suspend fun suspendfakeResult3(i: Int, j: Int): Int {
        delay(1000)
        return i + j
    }
}
