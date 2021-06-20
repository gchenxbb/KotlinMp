package com.kotl.jetpack.workmanager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class Sleep6Worker(context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        Log.d("kotlinworker","Sleep6Worker")
        delay(6000)
        return Result.success()
    }
}