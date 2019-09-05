package com.fyp.kweku.cbtoganisation.di

import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.GetTasksInteractorInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.viewtaskbyid.ViewTaskByIDController
import dagger.Module
import dagger.Provides

@Module
class ViewTaskByIdModule {

    @Provides
    fun providesViewTaskByIDController(getTasksInteractorInterface: GetTasksInteractorInterface): ViewTaskByIDController{
        return ViewTaskByIDController(getTasksInteractorInterface)
    }
}