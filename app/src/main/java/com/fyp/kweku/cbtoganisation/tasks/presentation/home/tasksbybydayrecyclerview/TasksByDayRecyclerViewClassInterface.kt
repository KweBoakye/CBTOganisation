package com.fyp.kweku.cbtoganisation.tasks.presentation.home.tasksbybydayrecyclerview

import androidx.lifecycle.LifecycleOwner
import com.fyp.kweku.cbtoganisation.tasks.presentation.TaskViewModel
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.HomeFragment
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel


interface TasksByDayRecyclerViewClassInterface {

    interface TasksByDayRecyclerViewClassListener{}

    interface TasksByDayRecyclerViewClassFragmentListener{
       fun launchDialog(taskID: String)
    }

    fun setTasks(tasks: MutableList<TaskPresentationModel>)
    //fun setFragmentListener(fragmentListener: TasksByDayRecyclerViewClassFragmentListener)

    //fun tasksByDayObserver(taskViewModel: TaskViewModel, viewLifecycleOwner: LifecycleOwner)

    //fun onDaySelected(date: String)


}