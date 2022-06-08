package com.example.kotlinsample.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.elyeproj.loaderviewlibrary.LoaderTextView
import com.example.kotlinsample.R
import com.example.kotlinsample.view.fragment.MutableLiveDataFragment
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MutableLiveDataActivity : AppCompatActivity() {

    private var txt_livedata: LoaderTextView? = null
    private var btn_control: Button? = null
    private var btn_livedata: Button? = null

    val liveDataA = MutableLiveData<String>()
    private val changeObserver = Observer<String> { value ->
        value?.let {
            txt_livedata?.text = it
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mutable_live_data)
        txt_livedata = findViewById(R.id.txt_livedata_a)
        btn_control = findViewById(R.id.btn_control_fragment)
        btn_livedata = findViewById(R.id.btn_livedata_a)
        title = "Mutable Live Data"

        if (savedInstanceState != null) {
            setFragmentControlButtonText()
        }

        liveDataA.observe(this, changeObserver)

        btn_livedata?.setOnClickListener {
            txt_livedata?.resetLoader()
            MainScope().launch {
                delay(1000)
                liveDataA.postValue((1..9999).random().toString())
            }
        }

        btn_control?.setOnClickListener {
            if (supportFragmentManager.backStackEntryCount == 0) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MutableLiveDataFragment())
                    .addToBackStack("").commit()
            } else {
                supportFragmentManager.popBackStack()
            }
        }

        supportFragmentManager.addOnBackStackChangedListener {
            setFragmentControlButtonText()
        }
    }

    private fun setFragmentControlButtonText() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            btn_control?.text = "Add Fragment"
        } else {
            btn_control?.text = "Remove Fragment"
        }
    }
}