package com.fyp.kweku.cbtoganisation.di

import android.app.Presentation
import com.fyp.kweku.cbtoganisation.tasks.presentation.createnewtask.CreateNewTaskController
import com.fyp.kweku.cbtoganisation.tasks.presentation.createnewtask.CreateNewTaskViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.createnewtask.CreateNewTaskViewClassInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.viewtasks.ViewTasksController
import com.fyp.kweku.cbtoganisation.tasks.presentation.viewtasks.ViewTasksViewClass
import com.fyp.kweku.cbtoganisation.tasks.presentation.viewtasks.ViewTasksViewClassInterface
import org.koin.dsl.module.module

val PresentationModule = module {
    factory<CreateNewTaskViewClassInterface> { CreateNewTaskViewClass(get(),get()) }

    factory{CreateNewTaskController(get())}

    factory<ViewTasksViewClassInterface> { ViewTasksViewClass(get(),get()) }

    factory { ViewTasksController(get()) }


}