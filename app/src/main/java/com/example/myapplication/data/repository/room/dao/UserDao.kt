package com.example.myapplication.data.repository.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.data.repository.room.data.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: UserEntity)

    @Query("SELECT * FROM example_table ORDER BY user_id ASC")
    fun readAllData(): LiveData<List<UserEntity>>

    //It will remove all rows from table
    @Query("DELETE FROM example_table")
    suspend fun removeAllUser()

    //Update greeting by specific user/id.
    @Query("UPDATE example_table SET name = :new_name, greeting = :new_greeting WHERE user_id =:id")
    fun editUserGreeting(id:Int, new_name:String, new_greeting:String)

}