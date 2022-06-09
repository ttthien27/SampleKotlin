package com.example.kotlinsample.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.elyeproj.loaderviewlibrary.LoaderTextView
import com.example.kotlinsample.R
import com.example.kotlinsample.databinding.ActivityMainBinding
import com.example.kotlinsample.databinding.ActivityMutableLiveDataBinding
import com.example.kotlinsample.databinding.ActivityMutableLiveDataBindingImpl
import com.example.kotlinsample.view.fragment.MutableLiveDataFragment
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MutableLiveDataActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMutableLiveDataBinding
    /*private var txt_livedata: LoaderTextView? = null
    private var btn_control: Button? = null
    private var btn_livedata: Button? = null*/

    val liveDataA = MutableLiveData<String>()
    private val changeObserver = Observer<String> { value ->
        value?.let {
            binding.txtLivedataA?.text = it
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_mutable_live_data)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mutable_live_data)
        binding.executePendingBindings()


        title = "Mutable Live Data"

        if (savedInstanceState != null) {
            setFragmentControlButtonText()
        }

        liveDataA.observe(this, changeObserver)
        binding.btnLivedataA?.setOnClickListener(this)
        binding.btnControlFragment?.setOnClickListener(this)

        supportFragmentManager.addOnBackStackChangedListener {
            setFragmentControlButtonText()
        }
    }

    private fun setFragmentControlButtonText() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            binding.btnControlFragment?.text = "Add Fragment"
        } else {
            binding.btnControlFragment?.text = "Remove Fragment"
        }
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.btn_livedata_a -> {
                    binding.txtLivedataA?.resetLoader()
                    MainScope().launch {
                        delay(1000)
                        liveDataA.postValue((1..9999).random().toString())
                    }
                }

                R.id.btn_control_fragment -> {
                    if (supportFragmentManager.backStackEntryCount == 0) {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, MutableLiveDataFragment())
                            .addToBackStack("").commit()
                    } else {
                        supportFragmentManager.popBackStack()
                    }
                }
            }
        }
    }
}