package com.kotl.jetpack.workmanager

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.*
import com.kotl.jetpack.R
import com.zch.demo.workmanager.*
import kotlinx.android.synthetic.main.activity_workmanager.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class WorkManagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workmanager)
        btnNWorker?.setOnClickListener { testNormalWorker() }
        btnPWorker?.setOnClickListener { testPeridoicWorker() }
        btnCWorker?.setOnClickListener { testTaskChainWorker() }
    }

    fun testNormalWorker1() {
        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)//联网
                .setRequiresStorageNotLow(true)//存储充足
                .setRequiresBatteryNotLow(true)//电量充足
                .setRequiresCharging(true)//充电时
                .build()

        var request = OneTimeWorkRequest.Builder(BaseWorker::class.java)
                .setConstraints(constraints)
                .build()

        WorkManager.getInstance(applicationContext).enqueue(request)

        WorkManager.getInstance(applicationContext).getWorkInfoByIdLiveData(request.id)
                .observe(this, Observer { workInfo ->
                    if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {

                    }
                })
    }


    fun testNormalWorker() {
        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)//联网
                .setRequiresStorageNotLow(true)//存储充足
                .setRequiresBatteryNotLow(true)//电量充足
                .setRequiresCharging(true)//充电时
                .build()

        val time = SimpleDateFormat("hh:mm:ss", Locale.getDefault()).format(Date())
        val inputData = workDataOf(
                "input_name" to "里斯",
                "input_id" to 12345,
                "input_time" to time)

        var uploadrequset = OneTimeWorkRequest.Builder(NormalWorker::class.java)
                .setConstraints(constraints)
                .setInputData(inputData)
                .build()

        Log.d("kotlinworker", "UI准备加入任务,${Thread.currentThread().name}线程")
        tv_workmanager_info_n.setText("UI准备加入任务:$time")

        WorkManager.getInstance(applicationContext).enqueue(uploadrequset)

        WorkManager.getInstance(applicationContext).getWorkInfoByIdLiveData(uploadrequset.id)
                .observe(this, Observer { workInfo ->
                    if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                        val name = workInfo.outputData.getString("out_name")
                        val id = workInfo.outputData.getInt("out_id", 0)
                        val time = workInfo.outputData.getString("out_time")

                        Log.d("kotlinworker", "UI接收：$name,$id,$time,${Thread.currentThread().name}线程")

                        var old = tv_workmanager_info_n.text;
                        var new = "$old\nUI接收:$name,$id,$time,${Thread.currentThread().name}线程"
                        tv_workmanager_info_n.setText(new)
                    }
                })
    }


    private fun testPeridoicWorker() {
        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        val peridoicReq = PeriodicWorkRequest.Builder(PeridoicWorker::class.java, 16, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build()

        WorkManager.getInstance(applicationContext).enqueue(peridoicReq)

        WorkManager.getInstance(applicationContext).getWorkInfoByIdLiveData(peridoicReq.id).observe(this, Observer { workInfo ->
            if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                Log.d("kotlinworker", "success-->")

//                tv_workmanager_info_p.setText()
            }
        })
    }


    private fun testTaskChainWorker() {
        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        val A = OneTimeWorkRequest.Builder(Sleep6Worker::class.java)
                .setConstraints(constraints)
                .build()
        val B = OneTimeWorkRequest.Builder(ChainWorkerB::class.java)
                .setConstraints(constraints)
                .build()
        val C = OneTimeWorkRequest.Builder(Sleep2Worker::class.java)
                .setConstraints(constraints)
                .build()
        val D = OneTimeWorkRequest.Builder(ChainWorkerD::class.java)
                .setConstraints(constraints)
                .build()
        val E = OneTimeWorkRequest.Builder(ChainWorkerE::class.java)
                .setConstraints(constraints)
                .build()
        var F = OneTimeWorkRequest.Builder(ChainWorkerF::class.java)
                .setConstraints(constraints)
                .build()

        //AB串行
        val chuan1 = WorkManager.getInstance(applicationContext)
                .beginWith(A)
                .then(B)
        //CD串行
        val chuan2 = WorkManager.getInstance(applicationContext)
                .beginWith(C)
                .then(D)

        //AB,CD并行，设为ABCD，ABCD和EF串行
        WorkContinuation.combine(listOf(chuan1, chuan2))
                .then(E)
                .then(F)
                .enqueue()
    }

}

