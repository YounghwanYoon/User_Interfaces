package com.example.myapplication.data.repository.room.data

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "repo_table")
data class RepoEntity(

    @NonNull
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val url: String,
    val full_name: String,
){}
