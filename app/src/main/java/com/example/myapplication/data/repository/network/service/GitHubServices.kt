package com.example.myapplication.data.repository.network.service

import com.example.myapplication.data.repository.network.model.Repo
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface GitHubServices {
    @GET("users/{user}/repos")
    suspend fun getRepos(@Path("user") user:String="younghwanyoon"): Response<Repo>

}
