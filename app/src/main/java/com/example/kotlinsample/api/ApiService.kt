package com.example.kotlinsample.api

import com.example.kotlinsample.model.Document
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @get:GET("search?query=thiên%20tai%20việt%20nam")
    val documents: Call<List<Document>>

    @GET("search?query=thiên%20tai%20việt%20nam")
    suspend fun getDocuments(): Response<List<Document>>
}
