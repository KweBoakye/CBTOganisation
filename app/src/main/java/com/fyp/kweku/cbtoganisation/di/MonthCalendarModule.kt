package com.fyp.kweku.cbtoganisation.di

import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.MonthCalendarInteractor
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.GetTasksInteractorInterface
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.MonthCalendarInteractorInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.viewpager.MonthViewPagerController
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar.MonthCalendarControllerForViewPager
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.taskbydaydialog.TasksBySpecificDayController
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module(includes = [MonthCalendarModule.MonthCalendarModuleInterface::class])
class MonthCalendarModule {


    @Provides
    fun providesMonthViewPagerController(getTasksInteractorInterface: GetTasksInteractorInterface): MonthViewPagerController {
        return MonthViewPagerController(
            getTasksInteractorInterface
        )
    }

    @Provides
    fun providesTasksBySpecificDayController(getTasksInteractorInterface:GetTasksInteractorInterface): TasksBySpecificDayController{
        return TasksBySpecificDayController(getTasksInteractorInterface)
    }

    @Provides
    fun providesMonthCalendarControllerForViewPager(getTasksInteractorInterface:GetTasksInteractorInterface,monthCalendarInteractorInterface: MonthCalendarInteractorInterface): MonthCalendarControllerForViewPager{
        return MonthCalendarControllerForViewPager(getTasksInteractorInterface, monthCalendarInteractorInterface)
    }

    @Module
    interface MonthCalendarModuleInterface{

        @Binds
        fun bindsMonthCalendarInteractorInterface(monthCalendarInteractor: MonthCalendarInteractor): MonthCalendarInteractorInterface

    }
}