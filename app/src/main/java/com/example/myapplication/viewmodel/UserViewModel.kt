package com.example.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.repository.UserRepository
import com.example.myapplication.room.data.UserEntity
import com.example.myapplication.room.database.UserDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


//view model is used to provide data to the UI without configuration change interruption.
// VM act as a middle man and communicate between Repository and UI to keep architecture separate.
//AndroidViewModel is used to provide application.context.
class UserViewModel(application: Application):AndroidViewModel(application) {

    private val readAllData: LiveData<List<UserEntity>>
    private val repository:UserRepository


    init{
        val userDao = UserDataBase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    //this is function to communicate with UI
    //Once UI pass new data to VM, VM will pass it to repository
    // where repository then using UserDao to store in UserDataBase.
    fun addUser(user:UserEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.addUser(user)
        }
    }

    fun getUser():LiveData<List<UserEntity>>{
        return readAllData
    }

    fun removeAllUser(){
        viewModelScope.launch(Dispatchers.IO){
            repository.removeAllUser()
        }
    }

}