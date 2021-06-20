package com.kotl.jetpack.workmanager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.delay

/**
 * 定时任务
 */
class PeridoicWorker(context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        Log.d("kotlinworker", "开始定时任务,${Thread.currentThread().name}线程")

        return Result.success()
    }
}