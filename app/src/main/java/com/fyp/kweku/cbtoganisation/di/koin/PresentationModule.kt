package com.fyp.kweku.cbtoganisation.di.koin

import com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces.TaskDeletionOutput
import com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces.TaskOutput
import com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces.TasksByLocationOutput
import com.fyp.kweku.cbtoganisation.tasks.presentation.viewmodels.TaskDeletionViewModel
import com.fyp.kweku.cbtoganisation.tasks.presentation.viewmodels.TaskViewModel
import com.fyp.kweku.cbtoganisation.tasks.presentation.createnewtask.CreateNewTaskController
import com.fyp.kweku.cbtoganisation.tasks.presentation.createnewtask.CreateNewTaskViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.createnewtask.CreateNewTaskViewClassInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.edittasks.EditTaskController
import com.fyp.kweku.cbtoganisation.tasks.presentation.edittasks.EditTaskViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.edittasks.EditTaskViewClassInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.HomeController
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.HomeViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.HomeViewClassInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.mvc.HorizontalCalendarController
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.mvc.HorizontalCalendarViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.mvc.HorizontalCalendarViewClassInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.tasksbybydayrecyclerview.TasksByDayController
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.tasksbybydayrecyclerview.TasksByDayRecyclerViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.tasksbybydayrecyclerview.TasksByDayRecyclerViewClassInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.locations.tasksbylocation.TasksByLocationController
import com.fyp.kweku.cbtoganisation.tasks.presentation.locations.tasksbylocation.TasksByLocationViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.locations.tasksbylocation.TasksByLocationViewClassInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.viewpager.MonthViewPagerController
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.viewpager.MonthViewPagerViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.viewpager.MonthViewPagerViewClassInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar.MonthCalendarViewClassForViewPager
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar.MonthCalendarViewClassForViewPagerInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.recyclerview.MonthViewAsRecyclerViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.recyclerview.MonthViewAsRecyclerViewClassInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.taskbydaydialog.TasksBySpecificDayController
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.taskbydaydialog.TasksBySpecificDayViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.taskbydaydialog.TasksBySpecificDayViewClassInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.viewmodels.TasksByLocationViewModel
import com.fyp.kweku.cbtoganisation.tasks.presentation.viewtaskbyid.ViewTaskByIDController
import com.fyp.kweku.cbtoganisation.tasks.presentation.viewtaskbyid.ViewTaskByIDViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.viewtaskbyid.ViewTaskByIDViewClassInterface


import org.koin.dsl.module

val PresentationModule = module {
    single<TaskOutput> {
        TaskViewModel(
        )
    }

    single<TaskDeletionOutput> { TaskDeletionViewModel() }

    //single<LocationsOutput> { LocationViewModel() }

    single<TasksByLocationOutput> { TasksByLocationViewModel()}

    factory<CreateNewTaskViewClassInterface> { CreateNewTaskViewClass(get(),get(),get()) }

    factory{ CreateNewTaskController(get())}



    factory<HomeViewClassInterface> { HomeViewClass(get(),get(), get())  }

    factory { HomeController() }

    factory<HorizontalCalendarViewClassInterface> { HorizontalCalendarViewClass(get(),get(),get())}

    factory { HorizontalCalendarController(get()) }





    factory<TasksByDayRecyclerViewClassInterface> { TasksByDayRecyclerViewClass(get(),get(), get()) }

    factory { TasksByDayController(get(), get()) }



    //locations
    //factory<LocationsViewClassInterface> { LocationsViewClass(get(), get(), get()) }
    //factory { LocationsController(get()) }

    //tasksByLocation
    factory<TasksByLocationViewClassInterface> { TasksByLocationViewClass(get(), get()) }
    factory { TasksByLocationController(get()) }

    factory<MonthViewPagerViewClassInterface> {
        MonthViewPagerViewClass(
            get(),
            get(),
            get()
        )
    }
    factory {
        MonthViewPagerController(
            get()
        )
    }

    factory <MonthCalendarViewClassForViewPagerInterface>{ MonthCalendarViewClassForViewPager(get(),get(), get(), get())  }
   // factory { MonthCalendarControllerForViewPager(get().get()) }

    factory<TasksBySpecificDayViewClassInterface> { TasksBySpecificDayViewClass(get(),get()) }
    factory { TasksBySpecificDayController(get()) }


    factory<ViewTaskByIDViewClassInterface> { ViewTaskByIDViewClass(get(), get()) }
    factory { ViewTaskByIDController(get()) }

    factory<EditTaskViewClassInterface> { EditTaskViewClass(get(), get()) }
    factory {  EditTaskController(get(), get()) }

    factory<MonthViewAsRecyclerViewClassInterface> {
        MonthViewAsRecyclerViewClass(
            get(),
            get()
        )
    }

    //factory<DeleteTasksViewClassInterface>{ DeleteTasksViewClass(get())}
   // factory { DeleteTasksController(get()) }





}