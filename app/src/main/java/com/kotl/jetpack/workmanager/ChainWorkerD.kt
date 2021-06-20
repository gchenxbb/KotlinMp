package com.zch.demo.workmanager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class ChainWorkerD(context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        Log.d("kotlinworker", "ChainWorkerD")
        return Result.success()
    }
}