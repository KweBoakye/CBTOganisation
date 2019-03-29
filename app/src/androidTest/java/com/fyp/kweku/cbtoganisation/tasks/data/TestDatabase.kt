package com.fyp.kweku.cbtoganisation.tasks.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.junit.After
import org.junit.Before
import java.io.IOException


abstract class TestDatabase{

protected lateinit var appDatabase: AppRoomDatabase

    @Before
    fun initDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder(
            context,
            AppRoomDatabase::class.java)
            .build()
    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        appDatabase.close()
    }
}