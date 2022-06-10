package com.example.kotlinsample.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.work.*
import com.example.kotlinsample.R
import com.example.kotlinsample.databinding.ActivityWorkManagerExampleBinding
import com.example.kotlinsample.worker.MyWorker
import com.example.kotlinsample.worker.WorkManagerExampleWorker
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import java.util.concurrent.TimeUnit


class WorkManagerExampleActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityWorkManagerExampleBinding
    private val workerExampleRequest = OneTimeWorkRequest.Builder(WorkManagerExampleWorker::class.java)
        .setInputData(createInputData())
        .build()
    private val myWorkerRequest = OneTimeWorkRequest.Builder(MyWorker::class.java).build()
    private val myConstraints = Constraints.Builder()
        .setRequiresCharging(true)
        .build()
    private val request = PeriodicWorkRequest
        .Builder(WorkManagerExampleWorker::class.java, 15, TimeUnit.MINUTES,30, TimeUnit.SECONDS)
        .setConstraints(Constraints.NONE)
        .build()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_work_manager_example)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_work_manager_example)
        binding.executePendingBindings()

        binding.btnWorkManagerRun.setOnClickListener(this)
        binding.btnWorkManagerState.setOnClickListener(this)
        binding.btnWorkManagerStatePeriodic.setOnClickListener(this)
        binding.btnWorkManagerCancel.setOnClickListener(this)
        binding.btnWorkManagerRunPeriodic.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.btn_WorkManager_Run -> {
                    WorkManager.getInstance()
                        //.beginWith(listOf(workRequest, workRequest_v2))
                        .beginWith(workerExampleRequest)
                        .then(myWorkerRequest)
                        .enqueue();

                }

                R.id.btn_WorkManager_State -> {
                    WorkManager.getInstance().getWorkInfoByIdLiveData(workerExampleRequest.id)
                        .observe(this, Observer { workInfo ->
                            if (workInfo != null) {
                                Log.d("Worker", "Worker State: - ${workInfo.state}")
                                if(workInfo.state.isFinished){
                                    Toast.makeText(this, workInfo.outputData.getString("success"), Toast.LENGTH_LONG)
                                        .show()
                                }
                            }
                        })
                }

                R.id.btn_WorkManager_StatePeriodic -> {
                    WorkManager.getInstance().getWorkInfoByIdLiveData(request.id)
                        .observe(this, Observer { workInfo ->
                            if (workInfo != null) {
                                Log.d("Worker", "Worker State: - ${workInfo.state}")
                            }
                        })
                }

                R.id.btn_WorkManager_Cancel -> {
                    // by id
                    WorkManager.getInstance().cancelWorkById(request.id)

                    // by name
                    //workManager.cancelUniqueWork("sync")

                    // by tag
                    //workManager.cancelAllWorkByTag("syncTag")
                }

                R.id.btn_WorkManager_RunPeriodic -> {
                    WorkManager.getInstance()
                        //.beginWith(listOf(workRequest, workRequest_v2))
                        .enqueue(request);
                }

            }
        }
    }

    private fun createInputData(): Data {
        return Data.Builder()
            .putInt("maxCount", 30)
            .build()
    }
}