package com.fyp.kweku.cbtoganisation.di

import android.app.Application
import android.content.Context
import com.fyp.kweku.cbtoganisation.tasks.presentation.TaskActivity
import com.fyp.kweku.cbtoganisation.tasks.presentation.createnewtask.CreateNewTaskFragment
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.HomeFragment
import com.fyp.kweku.cbtoganisation.tasks.presentation.locations.LocationsController
import com.fyp.kweku.cbtoganisation.tasks.presentation.locations.LocationsFragment
import com.fyp.kweku.cbtoganisation.tasks.presentation.locations.tasksbylocation.TasksByLocationDialogFragment
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.viewpager.MonthViewPagerFragment
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar.MonthCalendarControllerForViewPager
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar.MonthCalendarFragment
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.recyclerview.MonthViewAsRecyclerViewFragment
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.taskbydaydialog.TasksBySpecificDayDialogFragment
import com.fyp.kweku.cbtoganisation.tasks.presentation.viewtaskbyid.ViewTaskByIDFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules =[
DataLayerModule::class,
LocationModule::class,
CreateNewTaskModule::class,
TaskModule::class,
DeleteTaskModule::class,
    HomeModule::class,
MonthCalendarModule::class,
    ViewTaskByIdModule::class])
interface AppComponent {

    val locationsController: LocationsController
    val monthCalendarControllerForViewPager: MonthCalendarControllerForViewPager

    fun inject(taskActivity: TaskActivity)
    fun inject(createNewTaskFragment: CreateNewTaskFragment)
    fun inject(locationsFragment: LocationsFragment)
    fun inject(tasksByLocationDialogFragment: TasksByLocationDialogFragment)
    fun inject(homeFragment: HomeFragment)
    fun inject(monthViewPagerFragment: MonthViewPagerFragment)
    fun inject(monthCalendarFragment: MonthCalendarFragment)
    fun inject(viewTaskByIDFragment: ViewTaskByIDFragment)
    fun inject(taskbySpecificDayDialogFragment: TasksBySpecificDayDialogFragment)
    fun inject(monthViewAsRecyclerViewFragment: MonthViewAsRecyclerViewFragment)

    @Component.Builder
    interface Builder{
        @BindsInstance fun application(application: Application):Builder

        @BindsInstance fun bindContext(context: Context):Builder



        fun build():AppComponent
    }








}