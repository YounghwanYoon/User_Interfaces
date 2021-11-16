package com.example.myapplication.utils.states

import com.example.myapplication.R
import com.example.myapplication.data.repository.network.model.Repo

sealed class ResourceState<T>(
    val data: T? = null,
    val errMessage:Throwable? = null
){
    class Success<T>(data: T):ResourceState<T>(data)
    class Loading<T>(data:T? = null):ResourceState<T>(data)
    class Error<T>(data:T? =null, errMessage:Throwable,):ResourceState<T>(data, errMessage)
}
