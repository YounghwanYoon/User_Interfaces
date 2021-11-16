package com.example.myapplication.data.repository.room.data

import androidx.room.Entity
import androidx.room.PrimaryKey

//Entity of Room (which represent a table within the database such as variable/column/name/id/age/etc)
@Entity(tableName = "example_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    //@ColumnInfo(name = "id")
    val user_id:Int,
    val name:String,
    val greeting:String,
){
}
