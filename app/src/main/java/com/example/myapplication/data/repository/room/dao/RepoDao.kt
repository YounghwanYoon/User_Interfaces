package com.example.myapplication.data.repository.room.dao

import androidx.room.*
import com.example.myapplication.data.repository.room.data.RepoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RepoDao {

    @Query("Select * FROM repo_table order by id DESC")
    fun getAll(): Flow<List<RepoEntity>?>


/*
    @Query("Select * FROM repo_table WHERE full_name LIKE '%' || :searchQuery || '%' ORDER BY important DESC")
    fun getTaskBySearch(): Flow<List<RepoEntity>?>


 */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserAll(repos:List<RepoEntity>)


    @Delete
    fun delete(repo:RepoEntity)

    @Delete
    fun deleteAll(repos: List<RepoEntity>)

    @Query("DELETE FROM repo_table")
    fun deleteAll()

}