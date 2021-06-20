package com.kotl.jetpack.workmanager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay


class Sleep2Worker(context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        Log.d("kotlinworker","Sleep2Worker")
        delay(2000)
        return Result.success()
    }
}