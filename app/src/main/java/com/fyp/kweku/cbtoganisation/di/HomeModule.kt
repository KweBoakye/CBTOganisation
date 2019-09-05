package com.fyp.kweku.cbtoganisation.di

import com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces.TaskOutput
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.GetTasksInteractorInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.mvc.HorizontalCalendarController
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.tasksbybydayrecyclerview.TasksByDayController
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

        @Provides
        fun providesHorizontalCalendarController( getTasksInteractorInterface: GetTasksInteractorInterface): HorizontalCalendarController {
            return HorizontalCalendarController(getTasksInteractorInterface)
        }

        @Provides
        fun  providesTasksByDayController(getTasksInteractorInterface: GetTasksInteractorInterface, taskOutput : TaskOutput): TasksByDayController{
            return TasksByDayController(getTasksInteractorInterface, taskOutput)
        }


}