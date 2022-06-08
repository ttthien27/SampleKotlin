package com.example.kotlinsample.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.kotlinsample.R
import com.example.kotlinsample.view.activity.MutableLiveDataActivity

class MutableLiveDataFragment : androidx.fragment.app.Fragment() {

    var txt_fragment: TextView? = null
    private val changeObserver = Observer<String> { value ->
        value?.let {
            txt_fragment?.text = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View? = inflater.inflate(R.layout.fragment_mutable_live_data, container, false)
        txt_fragment = view?.findViewById(R.id.txt_fragment)
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MutableLiveDataActivity).liveDataA.observe(this, changeObserver)
    }
}