package com.fyp.kweku.cbtoganisation.di.koin

import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.*
import org.koin.dsl.module

val DomainModule = module{

    //single<CreateNewTaskInteractorInterface> {CreateNewTaskInteractor(get(),get()) }

    single<GetTasksInteractorInterface> { GetTasksInteractor(get(),get()) }

    //factory<LocationInteractorInterface> { LocationInteractor(get(), get())}

    single<EditTasksInteractorInterface> {EditTasksInteractor(get())}

    //single<DeleteAndOrRestoreTaskInteractorInterface> {DeleteAndOrRestoreTaskInteractor(get(), get(), get())}

    single<TasksByLocationInteractorInterface> {TasksByLocationInteractor(get(), get())}

}