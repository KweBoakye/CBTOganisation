package com.fyp.kweku.cbtoganisation.di

import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.CreateNewTaskInteractor
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.CreateNewTaskInteractorInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.createnewtask.CreateNewTaskController
import dagger.Binds
import dagger.Module


@Module(includes = [CreateNewTaskModule.CreateNewTaskModuleInterface::class])
class CreateNewTaskModule {

    fun providesCreateNewTaskController(createNewTaskInteractorInterface: CreateNewTaskInteractorInterface): CreateNewTaskController{
        return CreateNewTaskController(createNewTaskInteractorInterface)
    }

    @Module
    interface CreateNewTaskModuleInterface{
        @Binds
        fun bindCreateNewTaskInteractorInterface(createNewTaskInteractor: CreateNewTaskInteractor):CreateNewTaskInteractorInterface


    }
}