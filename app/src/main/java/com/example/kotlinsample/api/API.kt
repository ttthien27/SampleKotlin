package com.example.kotlinsample.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.kotlinsample.api.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import retrofit2.create

object API {
    private var retrofit: Retrofit? = null
    private var gson: Gson ? = null

    val apiService: ApiService
        get() {
            if (retrofit == null) {
                gson = GsonBuilder()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .create();
                retrofit = Retrofit.Builder()
                    .baseUrl("https://lucene-app-webservice.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }
            return retrofit!!.create(ApiService::class.java)
        }

    val apiService_v2: ApiService
        get() {
            if (retrofit == null) {
                gson = GsonBuilder()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .create();
                retrofit = Retrofit.Builder()
                    .baseUrl("https://lucene-app-webservice.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .build()
            }
            return retrofit!!.create(ApiService::class.java)
        }
}