package com.zch.demo.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class ChainWorkerB(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    /***
     * 耗时任务在doWork()方法
     */
    override fun doWork(): Result {
        Log.d("kotlinworker", "ChainWorkerB")
        return Result.success()
    }
}