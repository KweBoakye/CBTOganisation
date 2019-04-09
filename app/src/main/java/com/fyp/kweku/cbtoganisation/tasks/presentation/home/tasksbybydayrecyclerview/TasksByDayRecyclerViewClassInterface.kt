package com.fyp.kweku.cbtoganisation.tasks.presentation.home.tasksbybydayrecyclerview

import androidx.lifecycle.LifecycleOwner
import com.fyp.kweku.cbtoganisation.tasks.presentation.TaskViewModel
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.HomeFragment
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel


interface TasksByDayRecyclerViewClassInterface {

    interface TasksByDayRecyclerViewClassListener{}

    fun setTasks(tasks: MutableList<TaskPresentationModel>)

    //fun tasksByDayObserver(taskViewModel: TaskViewModel, viewLifecycleOwner: LifecycleOwner)

    //fun onDaySelected(date: String)


}