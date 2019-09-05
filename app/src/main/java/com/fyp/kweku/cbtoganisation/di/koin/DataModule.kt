package com.fyp.kweku.cbtoganisation.di.koin

import com.fyp.kweku.cbtoganisation.tasks.data.AppRoomDatabase
import com.fyp.kweku.cbtoganisation.tasks.data.TaskDao
import com.fyp.kweku.cbtoganisation.tasks.data.TaskRepository
import com.fyp.kweku.cbtoganisation.tasks.domain.repository.TaskRepositoryInterface
import org.koin.dsl.module



    val DataModule = module {

        // single instance of TaskRepository
        single<TaskRepositoryInterface> { TaskRepository(get()) }

        single { AppRoomDatabase.getDatabase(get()) }

        single { get<AppRoomDatabase>().taskDao() }



    }
