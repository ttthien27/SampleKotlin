package com.example.kotlinsample.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.kotlinsample.R
import com.example.kotlinsample.databinding.ActivityWorkManagerExampleBinding
import com.example.kotlinsample.worker.MyWorker
import com.example.kotlinsample.worker.WorkManagerExampleWorker
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class WorkManagerExampleActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var binding : ActivityWorkManagerExampleBinding
    val workRequest = OneTimeWorkRequest.Builder(WorkManagerExampleWorker::class.java).build()
    val workRequest_v2 = OneTimeWorkRequest.Builder(MyWorker::class.java).build()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_work_manager_example)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_work_manager_example)
        binding.executePendingBindings()

        binding.btnWorkManagerRun.setOnClickListener(this)
        binding.btnWorkManagerState.setOnClickListener(this)



    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.btn_WorkManager_Run -> {
                    WorkManager.getInstance()
                        .beginWith(listOf(workRequest, workRequest_v2))
                        .enqueue();

                }

                R.id.btn_WorkManager_State -> {
                    WorkManager.getInstance().getWorkInfoByIdLiveData(workRequest.id)
                        .observe(this, Observer { workInfo ->
                            if (workInfo != null) {
                                Log.d("Worker", "Worker State: - ${workInfo.state}")
                            }
                        })
                }

            }
        }
    }
}