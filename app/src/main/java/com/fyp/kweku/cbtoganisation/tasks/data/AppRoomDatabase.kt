package com.fyp.kweku.cbtoganisation.tasks.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.fyp.kweku.cbtoganisation.common.prepopulation.Prepoulator
import com.fyp.kweku.cbtoganisation.tasks.data.model.TaskDataModel
import timber.log.Timber
//define the entities and related classes and the version number
@Database(entities = [TaskDataModel::class], version = 5)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao    // Data Access Objects

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
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Thread(Runnable { prepopulateDb(getDatabase(context)) }).start()
                    }
                }).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }

        private fun prepopulateDb(db: AppRoomDatabase) {

           val a:List<Long> = db.taskDao().insertTasks(*Prepoulator.getResult()!!.toTypedArray())
            Timber.i("$a")
        }

    }
}






/*
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
*/
