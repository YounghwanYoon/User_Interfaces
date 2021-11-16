package com.example.myapplication.data.repository.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication.data.repository.room.dao.UserDao
import com.example.myapplication.data.repository.room.data.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 2
)
abstract class UserDataBase:RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE example_table RENAME COLUMN id TO user_id")
            }
        }


        @Volatile
        private var INSTANCE: UserDataBase? = null

        //make database as a singleton
        fun getDatabase(context: Context): UserDataBase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        UserDataBase::class.java,
                        "example_database"
                     )
                    .addMigrations(MIGRATION_1_2)
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }




}