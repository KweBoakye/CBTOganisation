package com.fyp.kweku.cbtoganisation.di

import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.CreateNewTaskInteractor
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.CreateNewTaskInteractorInterface
import dagger.Binds
import dagger.Module


@Module
abstract class CreateNewTaskModule {

        @Binds
        abstract fun bindCreateNewTaskInteractorInterface(createNewTaskInteractor: CreateNewTaskInteractor):CreateNewTaskInteractorInterface


    }
