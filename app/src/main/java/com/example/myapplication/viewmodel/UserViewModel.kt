package com.example.myapplication.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.data.repository.room.data.UserEntity
import com.example.myapplication.data.repository.room.database.UserDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


//view model is used to provide data to the UI without configuration change interruption.
// VM act as a middle man and communicate between Repository and UI to keep architecture separate.
//AndroidViewModel is used to provide application.context.
class UserViewModel(application: Application):AndroidViewModel(application) {

    private var readAllData: LiveData<List<UserEntity>>
    private val repository:UserRepository

    private val mutableSelectedUser = MutableLiveData<UserEntity>()
    val selectedUser:LiveData<UserEntity> get() = mutableSelectedUser

    fun selectedUser(position:Int){
        mutableSelectedUser.value = readAllData.value?.get(position)
        Log.d(TAG, "selectedUser: ${selectedUser.value}")
    }

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

    fun updateData(){
        readAllData = repository.readAllData
    }


    fun removeAllUser(){
        viewModelScope.launch(Dispatchers.IO){
            repository.removeAllUser()
        }
    }

    fun editUser(user_id:Int, new_user_name:String, new_user_greeting:String ){

        viewModelScope.launch(Dispatchers.IO){

            repository.editUserGreeting(user_id, new_user_name, new_user_greeting)
            //repository.editUserGreeting(new_greeting,id)
            Log.d(TAG, "editUserGreeting: from view model ")
        }

    }
    private val TAG:String = this.javaClass.name

}