package com.fyp.kweku.cbtoganisation.di

import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.*
import com.fyp.kweku.cbtoganisation.tasks.presentation.TaskViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val DomainModule = module{

    factory<CreateNewTaskInteractorInterface> {CreateNewTaskInteractor(get()) }
factory<GetTasksInteractorInterface> { GetTasksInteractor(get(),get()) }
   // single<TaskOutput> {  TaskViewModel()  }

}