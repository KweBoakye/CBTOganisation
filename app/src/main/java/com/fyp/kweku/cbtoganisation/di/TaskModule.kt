package com.fyp.kweku.cbtoganisation.di

import com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces.TaskOutput
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.GetTasksInteractor
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.GetTasksInteractorInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.viewmodels.TaskViewModel
import dagger.Binds
import dagger.Module

@Module(includes = [TaskModule.TaskModuleInterface::class])
class TaskModule {

    @Module
    interface TaskModuleInterface {
        @Binds
         fun bindsTaskOutput(taskViewModel: TaskViewModel): TaskOutput

        @Binds
        fun bindsGetTasksInteractorInterface(getTasksInteractor: GetTasksInteractor): GetTasksInteractorInterface
    }
}