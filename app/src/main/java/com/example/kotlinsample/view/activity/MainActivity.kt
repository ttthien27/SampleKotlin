package com.example.kotlinsample.view.activity

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

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mDocument: MutableList<Document> = mutableListOf()
    var documentAdapter: DocumentAdapter? = null
    private var documentViewModel = DocumentViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = documentViewModel
        binding.executePendingBindings()

    }

}