package com.example.myapplication.data.repository.network.model.post

import com.google.gson.annotations.SerializedName

data class Post(
    val userId:Int,
    val id: Int? = null, //it is good idea not to send id to server because it is usually wrong info.
    val title:String?,
    @SerializedName("body")
    val longText:String,

    ){}
