package com.example.kotlinsample.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.kotlinsample.R
import com.example.kotlinsample.databinding.ActivityCoroutineExampleBinding
import kotlinx.coroutines.*

class CoroutineExampleActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityCoroutineExampleBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_coroutine_example)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_coroutine_example)
        binding.executePendingBindings()

        binding.btnCoroutineRun.setOnClickListener(this)

    }


    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.btn_Coroutine_Run -> {
                    runExampleCoroutine()
                }
            }
        }
    }

    private fun runExampleCoroutine() {
        var indexCount: Int? = 0
        GlobalScope.launch {
            for (i in 1..3) {
                delay(1000L)
                Log.d("Bind", "Count index = $indexCount")
                delay(800L)
            }
        }

        GlobalScope.launch {
            delay(1800)
            for (i in 1..3) {
                Log.d("Bind", "Count v2 index = $indexCount")
                delay(200L)
            }
        }

        runBlocking {
            for (i in 1..3) {
                delay(800L)
                indexCount = indexCount?.plus(1)
                delay(1000L)
            }
        }
    }

}