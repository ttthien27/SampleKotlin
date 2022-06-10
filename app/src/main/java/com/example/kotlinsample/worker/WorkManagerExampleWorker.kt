package com.example.kotlinsample.worker

import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class WorkManagerExampleWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams)  {
    override fun doWork(): Result {
        return try {
            val maxCount = inputData.getInt("maxCount", -1)

            for (i:Int in 1..maxCount){
                runBlocking{
                    delay(300L)
                    Log.d("Worker", "doWork: Count = $i")
                    if (i==maxCount) displayNotification("WorkManagerExampleWorker", "Hey I finished my 1nd work")
                }
            }

            Result.success(createOutputData("----Success----"))
        } catch (e: Exception) {
            Result.failure()
        }

    }

    private fun displayNotification(title: String, task: String) {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "simplified coding",
                "simplified coding",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        val notification: NotificationCompat.Builder =
            NotificationCompat.Builder(applicationContext, "simplified coding")
                .setContentTitle(title)
                .setContentText(task)
                .setSmallIcon(R.mipmap.sym_def_app_icon)
        notificationManager.notify(1, notification.build())
    }

    private fun createOutputData(firstData: String): Data {
        return Data.Builder()
            .putString("success", firstData)
            .build()
    }
}