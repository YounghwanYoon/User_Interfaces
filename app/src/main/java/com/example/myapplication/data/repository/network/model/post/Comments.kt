package com.example.myapplication.data.repository.network.model.post

import com.google.gson.annotations.SerializedName

data class Comments(
    val id:Int,
    val postId:Int,
    val name:String,
    val email:String,

    @SerializedName("body")
    val text:String,



)
