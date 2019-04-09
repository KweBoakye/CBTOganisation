package com.fyp.kweku.cbtoganisation.di

import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.TaskOutput
import com.fyp.kweku.cbtoganisation.tasks.presentation.TaskViewModel
import com.fyp.kweku.cbtoganisation.tasks.presentation.createnewtask.CreateNewTaskController
import com.fyp.kweku.cbtoganisation.tasks.presentation.createnewtask.CreateNewTaskViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.createnewtask.CreateNewTaskViewClassInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.HomeController
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.HomeViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.HomeViewClassInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.mvc.HorizontalCalendarController
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.mvc.HorizontalCalendarViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.mvc.HorizontalCalendarViewClassInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.tasksbybydayrecyclerview.TasksByDayController
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.tasksbybydayrecyclerview.TasksByDayRecyclerViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.tasksbybydayrecyclerview.TasksByDayRecyclerViewClassInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.viewtasks.ViewTasksController
import com.fyp.kweku.cbtoganisation.tasks.presentation.viewtasks.ViewTasksViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.viewtasks.ViewTasksViewClassInterface
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module

val PresentationModule = module {
    factory<CreateNewTaskViewClassInterface> { CreateNewTaskViewClass(get(),get()) }

    factory{ CreateNewTaskController(get())}

    factory<ViewTasksViewClassInterface> { ViewTasksViewClass(get(),get()) }

    factory { ViewTasksController(get()) }

    factory<HomeViewClassInterface> { HomeViewClass(get(),get())  }

    factory { HomeController() }

    factory<HorizontalCalendarViewClassInterface> { HorizontalCalendarViewClass(get(),get(),get())}

    factory { HorizontalCalendarController(get()) }

     viewModel { TaskViewModel() }


    factory<TasksByDayRecyclerViewClassInterface> { TasksByDayRecyclerViewClass(get(),get(), get()) }

    factory { TasksByDayController(get()) }


}