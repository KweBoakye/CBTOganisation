package com.fyp.kweku.cbtoganisation.di

import com.fyp.kweku.cbtoganisation.tasks.data.TaskDao
import com.fyp.kweku.cbtoganisation.tasks.data.TaskRepository
import com.fyp.kweku.cbtoganisation.tasks.domain.repository.TaskRepositoryInterface
import org.koin.dsl.module.module

val taskRepositoryModule = module {

    // single instance of TaskRepository
    single<TaskRepositoryInterface> {TaskRepository(get()) }


}