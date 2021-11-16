package com.example.myapplication.data.repository.network.service

import com.example.myapplication.data.repository.network.model.post.Comments
import com.example.myapplication.data.repository.network.model.post.Post
import retrofit2.Call
import retrofit2.http.*

interface PlaceHolderApi {

    @GET("posts")
    fun getPosts(@Query("userId") userId:Int): Call<List<Post>>

    @GET("posts")
    fun getCustomizedPosts(@QueryMap parameters:Map<String, String>):Call<List<Post>>


    @GET("posts/{id}/comments")
    fun getComments(@Path("id") postId:Int):Call<List<Comments>>


    //Since this is not the actual server, it will return response code whether it was successful or not.
    @POST("posts")
    fun createPost(@Body post:Post): Call<Post>

    @FormUrlEncoded
    @POST("posts")
    fun createPost(
        @Field("userId") userId:Int,
        @Field("title") title:String,
        @Field("body") longText:String,

    ):Call<Post>


    @FormUrlEncoded
    @POST("posts")
    fun createPost(
        @FieldMap postMap:Map<String,String>
    ): Call<Post>


    @Headers("Static-Header:123","Testing-Header2:456")
    //Put will replace entire post data with data new post
    @PUT("posts/{id}")
    fun putPost(
        @Header("Dynamic-Header:") header:String,
        @Path("id") id:Int,
        @Body post:Post)
    :Call<Post>

    //Patch will only change only fields that we changed
    @PATCH("posts/{id}")
    fun patchPost(@Path("id") id:Int, @Body post:Post):Call<Post>

    @DELETE("posts/{id}")
    fun deletePost(@Path("id") id:Int):Call<Post>


}