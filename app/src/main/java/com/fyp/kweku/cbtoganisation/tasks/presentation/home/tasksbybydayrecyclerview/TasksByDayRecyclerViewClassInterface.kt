package com.fyp.kweku.cbtoganisation.tasks.presentation.home.tasksbybydayrecyclerview

import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel


interface TasksByDayRecyclerViewClassInterface{

    interface TasksByDayRecyclerViewClassListener{
        fun deleteTask(taskID: String)
    }

    interface TasksByDayRecyclerViewClassFragmentListener{
       fun launchDialog(taskID: String)
    }

    fun setTasks(tasks: MutableList<TaskPresentationModel>)

    fun setTasksByDayRecyclerViewClassListener(tasksByDayRecyclerViewClassListener: TasksByDayRecyclerViewClassListener)

    //fun setFragmentListener(fragmentListener: TasksByDayRecyclerViewClassFragmentListener)

    //fun tasksByDayObserver(taskViewModel: TaskViewModel, viewLifecycleOwner: LifecycleOwner)

    //fun onDaySelected(date: String)


}