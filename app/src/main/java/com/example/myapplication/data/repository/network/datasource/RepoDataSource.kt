package com.example.myapplication.data.repository.network.datasource

import com.example.myapplication.data.repository.network.model.Repo
import com.example.myapplication.data.repository.network.service.GitHubServices
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RepoDataSource(){

    companion object{
        val baseURL = "https://api/github.com/"
        var retrofit: Retrofit? = null

        fun getGitHubRetrofit():Retrofit{
            val tempRetrofit = retrofit
            if(tempRetrofit != null){
                return tempRetrofit
            }
            synchronized(this){
                val gson = GsonBuilder()
                    .setLenient()
                    .create()
                val okHttpClient = OkHttpClient.Builder()
                    .readTimeout(100, TimeUnit.SECONDS)
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .build()
                val githubRetrofit = Retrofit.Builder()
                    .baseUrl(baseURL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                retrofit = githubRetrofit
                return githubRetrofit
            }
        }

        fun getGithubService(): GitHubServices {
            val gitHubRetrofit = getGitHubRetrofit()
            return gitHubRetrofit.create(GitHubServices::class.java)
        }
    }

    suspend fun fetchRepos():Result<Repo>{
        val gitHubService = getGithubService()

        return getResponse(
            request = {gitHubService.getRepos()},
            errorMSG = "Failed to fetch data from $baseURL"
        )

    }

    private suspend fun <T> getResponse(
        request: suspend() -> Response<T>,
        errorMSG: String): Result<T>{

        return try{
            val result = request.invoke()
            if(result.isSuccessful)
                return Result.success(result.body()!!)
            else{
                return Result.failure(Throwable(errorMSG))
            }
        }catch(e:Throwable){
            Result.failure(Throwable("Unknown Error"))
        }

    }

}