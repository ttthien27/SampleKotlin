package com.example.kotlinsample.viewmodel


import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import com.example.kotlinsample.BR
import com.example.kotlinsample.api.API
import com.example.kotlinsample.model.Document
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DocumentViewModel : BaseObservable(), Callback<List<Document>> {

    private var listDocument: MutableList<Document> = mutableListOf()

    var title = "TITLE"
        @Bindable get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.title);
        }

    var description = "description"
        @Bindable get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.description);
        }

    var parapraphShort = "parapraph"
        @Bindable get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.parapraphShort);
        }


    fun onClick() {
        initData()
    }

    fun initData() {
        Log.d("API", "initData: run")
        API.apiService.documents.enqueue(this)
    }

    override fun onResponse(call: Call<List<Document>>, response: Response<List<Document>>) {
        if (response.body() == null) {
            Log.d("API", "Callnull: run")
            return
        }
        Log.d("API", "Call: run")

        listDocument.addAll(response.body()!!)
        title = listDocument?.get(1)?.docTitle.toString()
        Log.d("Bind", "ViewModel Title = $title")
        description = listDocument?.get(1)?.docDescription.toString()
        parapraphShort = listDocument?.get(1).docParagraphShort.toString()
    }

    override fun onFailure(call: Call<List<Document>>, t: Throwable) {

    }


}