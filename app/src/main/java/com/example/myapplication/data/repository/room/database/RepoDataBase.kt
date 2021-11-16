package com.example.myapplication.data.repository.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.data.repository.room.dao.RepoDao
import com.example.myapplication.data.repository.room.data.RepoEntity


@Database(entities= [RepoEntity::class],
        version = 1
    )
abstract class RepoDataBase: RoomDatabase() {

    abstract fun repoDao(): RepoDao

    companion object {

        @Volatile
        private var INSTANCE:RepoDataBase? = null

        fun getDatabase(context: Context):RepoDataBase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        RepoDataBase::class.java,
                        "github_repo_database"
                    )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }


}