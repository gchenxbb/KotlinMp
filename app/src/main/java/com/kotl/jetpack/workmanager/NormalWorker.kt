package com.kotl.jetpack.workmanager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.delay


class NormalWorker(context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        Log.d("kotlinworker", "开始执行,${Thread.currentThread().name}线程")

        //延迟500ms
        delay(500)

        val name = inputData.getString("input_name")
        val id = inputData.getInt("input_id", 1)
        val time = inputData.getString("input_time")

        val outputData = workDataOf("out_name" to "name:$name", "out_id" to "$id", "out_time" to "time:$time")

        Log.d("kotlinworker", "执行结束,${Thread.currentThread().name}线程")

        return Result.success(outputData)
    }
}