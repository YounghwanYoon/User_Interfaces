package com.example.myapplication.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.room.dao.UserDao
import com.example.myapplication.room.data.UserEntity


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

}