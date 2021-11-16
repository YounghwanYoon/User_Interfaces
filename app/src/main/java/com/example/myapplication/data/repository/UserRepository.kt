package com.example.myapplication.data.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.data.repository.room.dao.UserDao
import com.example.myapplication.data.repository.room.data.UserEntity


//Repository is a class access to multiple data sources and communicate/access to handle a data between database and view-model.
//Not part of architecture component but use to separate with architecture to be clean code
class UserRepository (private val userDao: UserDao){

    //calling userDao to access data from database.
    val readAllData: LiveData<List<UserEntity>> = userDao.readAllData()

    //sending view-model new data to database by calling userDao.addUser()
    //suspend signature is added to update new data asynchronously by coroutine flow.
    suspend fun addUser(newUserEntity:UserEntity){
        userDao.addUser(newUserEntity)
    }

    suspend fun removeAllUser(){
        userDao.removeAllUser()
    }

    suspend fun editUserGreeting(user_id:Int, user_name:String, user_greeting:String ){
        userDao.editUserGreeting(user_id, user_name, user_greeting)
    }
}