package com.fyp.kweku.cbtoganisation.di

import com.fyp.kweku.cbtoganisation.tasks.domain.TaskPresenter
import com.fyp.kweku.cbtoganisation.tasks.domain.TaskOutput
import com.fyp.kweku.cbtoganisation.tasks.presentation.TaskViewModel
import com.fyp.kweku.cbtoganisation.tasks.presentation.createnewtask.CreateNewTaskController
import com.fyp.kweku.cbtoganisation.tasks.presentation.createnewtask.CreateNewTaskViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.createnewtask.CreateNewTaskViewClassInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.deletetask.DeleteTasksViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.deletetask.DeleteTasksViewClassInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.HomeController
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.HomeViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.HomeViewClassInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.mvc.HorizontalCalendarController
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.mvc.HorizontalCalendarViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.mvc.HorizontalCalendarViewClassInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.tasksbybydayrecyclerview.TasksByDayController
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.tasksbybydayrecyclerview.TasksByDayRecyclerViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.tasksbybydayrecyclerview.TasksByDayRecyclerViewClassInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.locations.LocationsController
import com.fyp.kweku.cbtoganisation.tasks.presentation.locations.LocationsViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.locations.LocationsViewClassInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.locations.tasksbylocation.TasksByLocationController
import com.fyp.kweku.cbtoganisation.tasks.presentation.locations.tasksbylocation.TasksByLocationViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.locations.tasksbylocation.TasksByLocationViewClassInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthcalendar.MonthCalendarViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthcalendar.MonthCalendarViewClassInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthcalendar.outerrecyclerview.MonthCalendarOuterController
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthcalendar.outerrecyclerview.MonthCalendarOuterViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthcalendar.outerrecyclerview.MonthCalendarOuterViewClassInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.MonthViewPagerController
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.MonthViewPagerViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.MonthViewPagerViewClassInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar.MonthCalendarControllerForViewPager
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar.MonthCalendarViewClassForViewPager
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar.MonthCalendarViewClassForViewPagerInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.taskbydaydialog.TasksBySpecificDayController
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.taskbydaydialog.TasksBySpecificDayViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.taskbydaydialog.TasksBySpecificDayViewClassInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.viewtasks.ViewTasksController
import com.fyp.kweku.cbtoganisation.tasks.presentation.viewtasks.ViewTasksViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.viewtasks.ViewTasksViewClassInterface
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module

val PresentationModule = module {
    single<TaskOutput> {  TaskViewModel(get())  }


    factory<CreateNewTaskViewClassInterface> { CreateNewTaskViewClass(get(),get(),get()) }

    factory{ CreateNewTaskController(get())}

    factory<ViewTasksViewClassInterface> { ViewTasksViewClass(get(),get()) }

    factory { ViewTasksController(get()) }

    factory<HomeViewClassInterface> { HomeViewClass(get(),get())  }

    factory { HomeController() }

    factory<HorizontalCalendarViewClassInterface> { HorizontalCalendarViewClass(get(),get(),get())}

    factory { HorizontalCalendarController(get()) }

     viewModel { TaskViewModel(get() ) }

    factory { TaskPresenter() }


    factory<TasksByDayRecyclerViewClassInterface> { TasksByDayRecyclerViewClass(get(),get(), get()) }

    factory { TasksByDayController(get(), get()) }

    factory<MonthCalendarViewClassInterface> { MonthCalendarViewClass(get(), get()) }

    factory<MonthCalendarOuterViewClassInterface> { MonthCalendarOuterViewClass( get(), get() )    }

    factory { MonthCalendarOuterController(get() ) }

    //locations
    factory<LocationsViewClassInterface> { LocationsViewClass(get(), get()) }
    factory { LocationsController(get()) }

    //tasksByLocation
    factory<TasksByLocationViewClassInterface> { TasksByLocationViewClass(get(), get()) }
    factory { TasksByLocationController(get()) }

    factory<MonthViewPagerViewClassInterface> { MonthViewPagerViewClass(get(),get(),get())}
    factory { MonthViewPagerController(get()) }

    factory <MonthCalendarViewClassForViewPagerInterface>{ MonthCalendarViewClassForViewPager(get(),get())  }
    factory { MonthCalendarControllerForViewPager(get()) }

    factory<TasksBySpecificDayViewClassInterface> { TasksBySpecificDayViewClass(get(),get()) }
    factory { TasksBySpecificDayController(get()) }

    factory<DeleteTasksViewClassInterface>{ DeleteTasksViewClass() }



}