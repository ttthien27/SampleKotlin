package com.example.kotlinsample.view.activity

import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.kotlinsample.R
import com.example.kotlinsample.R.*
import com.example.kotlinsample.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), View.OnClickListener{

    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)

        binding = DataBindingUtil.setContentView(this, layout.activity_login)
        binding.executePendingBindings()

        binding.imgBtnLoginShowPass.setOnClickListener(this)

    }


    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.imgBtn_Login_ShowPass -> {
                    if(binding.txtInputPass.transformationMethod.equals(PasswordTransformationMethod.getInstance()))
                    {
                        binding.txtInputPass.transformationMethod = HideReturnsTransformationMethod.getInstance();
                        binding.txtInputPass.text?.let { binding.txtInputPass.setSelection(it.length) }
                    }else{
                        binding.txtInputPass.transformationMethod = PasswordTransformationMethod.getInstance();
                        binding.txtInputPass.text?.let { binding.txtInputPass.setSelection(it.length) }
                    }
                }
            }
        }
    }


}