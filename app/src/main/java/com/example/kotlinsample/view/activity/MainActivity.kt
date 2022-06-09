package com.example.kotlinsample.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.example.kotlinsample.R
import com.example.kotlinsample.adapter.DocumentAdapter
import com.example.kotlinsample.databinding.ActivityMainBinding

import com.example.kotlinsample.model.Document
import com.example.kotlinsample.viewmodel.DocumentViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var documentViewModel = DocumentViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = documentViewModel
        binding.executePendingBindings()

        binding.btnLoginClick.setOnClickListener(this)
        binding.btnLiveDataClick.setOnClickListener(this)
        binding.btnCoroutineClick.setOnClickListener(this)
        binding.btnWorkMangerClick.setOnClickListener(this)


    }




    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.btn_Login_Click -> {
                    val intent: Intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }

                R.id.btn_LiveData_Click -> {
                    val intent: Intent = Intent(this, MutableLiveDataActivity::class.java)
                    startActivity(intent)
                }

                R.id.btn_Coroutine_Click -> {
                    val intent: Intent = Intent(this, CoroutineExampleActivity::class.java)
                    startActivity(intent)
                }

                R.id.btn_WorkManger_Click -> {
                    val intent: Intent = Intent(this, WorkManagerExampleActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

}