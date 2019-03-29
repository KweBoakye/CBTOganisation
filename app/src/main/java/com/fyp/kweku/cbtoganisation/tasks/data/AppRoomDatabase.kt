package com.fyp.kweku.cbtoganisation.tasks.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fyp.kweku.cbtoganisation.tasks.data.model.TaskDataModel

@Database(entities = [TaskDataModel::class], version = 1)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getDatabase(context: Context): AppRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppRoomDatabase::class.java,
                    "App_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}