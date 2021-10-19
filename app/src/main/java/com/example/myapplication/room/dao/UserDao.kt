package com.example.myapplication.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.room.data.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: UserEntity)

    @Query("SELECT * FROM example_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<UserEntity>>


    //It will remove all rows from table
    @Query("DELETE FROM example_table")
    suspend fun removeAllUser()

}