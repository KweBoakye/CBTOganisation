package com.fyp.kweku.cbtoganisation.tasks.presentation.locations.tasksbylocation

import android.content.Context
import android.view.View
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel

interface TasksByLocationViewClassInterface {

    interface TasksByLocationViewClassListener{

    }

    interface TasksByLocationViewClassFragmentListener{
        fun dismissDialogFragment()

    }

    fun setFragmentListener(tasksByLocationViewClassFragmentListener: TasksByLocationViewClassFragmentListener)
    fun getRoot(): View
    fun setupToolbar()
    fun initRecyclerView(context: Context)
    fun setLocation(location:String?)

   fun sendDataToAdapter(tasks: List<TaskPresentationModel>)

}