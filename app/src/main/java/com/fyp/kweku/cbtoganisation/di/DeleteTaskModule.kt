package com.fyp.kweku.cbtoganisation.di

import com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces.TaskDeletionOutput
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.DeleteAndOrRestoreTaskInteractor
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.DeleteAndOrRestoreTaskInteractorInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.deletetask.DeleteTasksController
import com.fyp.kweku.cbtoganisation.tasks.presentation.viewmodels.TaskDeletionViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [DeleteTaskModule.DeleteTaskModuleInterface::class])
class DeleteTaskModule {

    @Provides
    fun provideDeleteTasksController(deleteAndOrRestoreTaskInteractorInterface : DeleteAndOrRestoreTaskInteractorInterface):DeleteTasksController{
        return DeleteTasksController(deleteAndOrRestoreTaskInteractorInterface)
    }

    @Module
    interface DeleteTaskModuleInterface{
        @Binds
        fun bindsDeleteAndOrRestoreTaskInteractorInterface(deleteAndOrRestoreTaskInteractor: DeleteAndOrRestoreTaskInteractor):DeleteAndOrRestoreTaskInteractorInterface

        @Binds
        fun bindsTaskDeletionOutput(taskDeletionViewModel: TaskDeletionViewModel): TaskDeletionOutput
    }
}