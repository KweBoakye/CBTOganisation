package com.fyp.kweku.cbtoganisation.di


import android.content.Context
import com.fyp.kweku.cbtoganisation.tasks.data.AppRoomDatabase
import com.fyp.kweku.cbtoganisation.tasks.data.TaskDao
import com.fyp.kweku.cbtoganisation.tasks.data.TaskRepository
import com.fyp.kweku.cbtoganisation.tasks.domain.repository.TaskRepositoryInterface
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataLayerModule {

    @Provides
    fun providesAppRoomDatabase(context: Context): AppRoomDatabase {
        return AppRoomDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun providesTaskDao(appRoomDatabase: AppRoomDatabase): TaskDao{
        return appRoomDatabase.taskDao()
    }


    @Provides
    @Singleton
    fun providesTaskRepository(taskDao: TaskDao): TaskRepositoryInterface{
        return TaskRepository(taskDao)
    }



}