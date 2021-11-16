package com.example.myapplication.ui.main_fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.R
import com.example.myapplication.data.repository.network.model.post.Comments
import com.example.myapplication.data.repository.network.model.post.Post
import com.example.myapplication.data.repository.network.service.PlaceHolderApi
import com.example.myapplication.databinding.FragmentSecondBinding
import com.google.android.material.snackbar.Snackbar
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SecondFragment: Fragment(R.layout.fragment_second) {
    private lateinit var _binding: FragmentSecondBinding
    private val TAG:String = this.javaClass.name


    companion object{
        val baseURL = "https://jsonplaceholder.typicode.com/"

        private var INSTANCE:Retrofit? = null
        fun getRetrofit():Retrofit {
            val instance:Retrofit
            if(INSTANCE != null){
                instance = INSTANCE as Retrofit
                return instance
            }

            //val gson = GsonBuilder().create()
            val gson = GsonBuilder().create()
            val logginInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
            logginInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient
                .Builder()
                .addInterceptor(logginInterceptor)
                .build()

            instance = Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build()

            INSTANCE = instance
            return instance
        }
    }

    private fun testPOSTAPI(){
        val retrofit = getRetrofit()
        val placeholderAPI = retrofit.create(PlaceHolderApi::class.java)

        createPost(placeholderAPI).enqueue(object:Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {

                if(!response.isSuccessful){
                    Log.d(TAG, "onResponse: post/server is not available and response code is ${response.code()}")
                    return
                }
                Log.d(TAG, "onResponse: post is successful response code is ${response.code()}")

                //This will return post body which return what we submitted/created from server side
                val postResponse:Post = response.body()!!
                Log.d(TAG, "onResponse: post is successful submitted body is ${postResponse}")



            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
                TODO("Not yet implemented")
            }
        })
    }


    private fun createPost(api:PlaceHolderApi):Call<Post>{
        val newPost = Post(13, null, "new Title", "Text is new text is word...omg what is wrong with me")
        //val call:Call<Post> = api.createPost(newPost)
        //val call:Call<Post> = api.createPost(123,"different new title", "loooooooooong TEXT")

        val fields:HashMap<String,String> = HashMap()
        fields.put("userId", "123")
        fields.put("title", "New Title")


        val call:Call<Post> = api.createPost(fields)
        return call
    }


    private fun testGetPostAPI(){
        val retrofit = getRetrofit()
        val placeholderAPI = retrofit.create(PlaceHolderApi::class.java)

        val call = placeholderAPI.getPosts(1)
        call.enqueue(object:Callback<List<Post>>{
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                //check whether response was successful such as sometimes data is not available
                //response.code() will return http response code (such as 404 data not available)
                if(!response.isSuccessful){
                    //if actual response is not successful, then log code number then stop
                    Log.d(TAG, "onResponse: ${response.code()}")
                    return
                }

                Log.d(TAG, "onResponse: ${response.body()?.get(0)?.title}")
                Snackbar.make(view!!, "Download Success ${response.body()?.get(0)?.title}", Snackbar.LENGTH_SHORT)
                    .show()
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
                Snackbar.make(view!!, "Failed to Download - ${t.message}", Snackbar.LENGTH_SHORT)
                    .show()
            }
        })


    }

    private fun testCommentAPI(){
        val retrofit = getRetrofit()
        val placeholderAPI = retrofit.create(PlaceHolderApi::class.java)

        val call = placeholderAPI.getComments(3)

        call.enqueue(object:Callback<List<Comments>>{
            override fun onResponse(call: Call<List<Comments>>, response: Response<List<Comments>>) {
                if(!response.isSuccessful){
                    Log.d(TAG, "onResponse: Response but error with code: ${response.code()}")
                    return //since there is no data, it should be stop or app will be crashed
                }

                Log.d(TAG, "onResponse: Successful and data is ${response.body()?.get(0)}}")
                
                
            }

            override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
                Log.d(TAG, "onFailure: Failed  and data is ${t.message}")
            }


        })



    }

    private fun testUpdateAPI(){
        val retrofit = getRetrofit()
        val placeholderAPI = retrofit.create(PlaceHolderApi::class.java)
        updatePost(placeholderAPI).enqueue(object:Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {

                if(!response.isSuccessful){
                    Log.d(TAG, "onResponse: something went wrong and code is ${response.code()}")
                    return
                }

                Log.d(TAG, "onResponse: successful and code is ${response.code()}")
                Log.d(TAG, "onResponse: successful and body is ${response.body()}")



            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
    }




    private fun testDeleteAPI(){
        val retrofit = getRetrofit()
        val API = retrofit.create(PlaceHolderApi::class.java)

        deletePost(API).enqueue(object:Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if(!response.isSuccessful){
                    Log.d(TAG, "onResponse: something went wrong with server side and code is  ${response.code()}")
                    return 
                }
                Log.d(TAG, "onResponse: went well and code is ${response.code()}")
                Log.d(TAG, "onResponse: went well and response body is ${response.body()}")
                
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }

    private fun updatePost(placeholderAPI:PlaceHolderApi):Call<Post>{
        val post:Post = Post(12,5, null, "New Text")
        val call:Call<Post> = placeholderAPI.putPost("34567",5, post)
        //onResponse: successful and body is Post(userId=12, id=5, title=null, longText=New Text)
        //val call:Call<Post> = placeholderAPI.patchPost(5, post)
        //onResponse: successful and body is Post(userId=12, id=5, title=nesciunt quas odio, longText=New Text)
        //onResponse: successful and body is Post(userId=12, id=5, title=null, longText=New Text)
        return call
    }

    private fun deletePost(placeholderAPI:PlaceHolderApi):Call<Post>{
        val call:Call<Post> = placeholderAPI.deletePost(5)
        return call
    }

    private var binding:FragmentSecondBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container,false)
        binding = _binding
        val view = binding?.root
        return view!!
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.let {
            it.secondText.setOnClickListener{
                NavHostFragment.findNavController(this).navigate(R.id.action_secondFragment_to_thirdFragment)
            }

        }
        //testDeleteAPI()
        testUpdateAPI()
        //testPOSTAPI()
        //testGetPostAPI()
        //testCommentAPI()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

}