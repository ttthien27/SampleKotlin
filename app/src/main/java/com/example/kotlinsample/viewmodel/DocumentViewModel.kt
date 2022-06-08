package com.example.kotlinsample.viewmodel


import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.kotlinsample.BR
import com.example.kotlinsample.api.API
import com.example.kotlinsample.model.Document
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Flow

class DocumentViewModel : BaseObservable(), Callback<List<Document>> {

    private var listDocument: MutableList<Document> = mutableListOf()
        @Bindable get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.listDocument);
        }


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
        Log.d("API", "onClick: run")

    }

    private fun changeData() = runBlocking {
        flowPosition().collect() {
            title = listDocument?.get(it)?.docTitle.toString()
            description = listDocument?.get(it)?.docDescription.toString()
            Log.d("BindTitle", "ViewModel Des $it = $description ")
            parapraphShort = listDocument?.get(it).docParagraphShort.toString()
            notifyChange()
        }
    }


    private fun initData() {
        Log.d("API", "initData: run")
        API.apiService.documents.enqueue(this)
        //changeData()
    }

    override fun onResponse(call: Call<List<Document>>, response: Response<List<Document>>) {
        if (response.body() == null) {
            Log.d("API", "Callnull: run")
            return
        }
        Log.d("API", "Call: run")
        listDocument.addAll(response.body()!!)
        changeData()
    }

    override fun onFailure(call: Call<List<Document>>, t: Throwable) {

    }

    private fun flowPosition() = flow {
        // flow builder
        for (i in 1..3) {
            emit(i) // emit next value
            delay(5000)
        }
    }
}