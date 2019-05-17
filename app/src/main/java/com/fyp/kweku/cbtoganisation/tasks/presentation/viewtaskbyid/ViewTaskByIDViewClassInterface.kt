package com.fyp.kweku.cbtoganisation.tasks.presentation.viewtaskbyid

import android.view.View
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel

interface ViewTaskByIDViewClassInterface {

    interface ViewTaskByIDViewClassListener{

    }

    interface ViewTaskByIDViewClassFragmentListener{
        fun dismissDialogFragment()
        fun launchEditTaskFragment()
    }

    fun getRoot(): View
     fun setFragmentListener(fragmentListener: ViewTaskByIDViewClassFragmentListener)
    fun populateTextViews(task: TaskPresentationModel)
    fun setupToolbar()
}