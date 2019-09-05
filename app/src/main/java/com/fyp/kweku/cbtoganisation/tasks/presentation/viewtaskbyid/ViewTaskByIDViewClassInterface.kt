package com.fyp.kweku.cbtoganisation.tasks.presentation.viewtaskbyid

import android.view.View
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import com.fyp.kweku.cbtoganisation.tasks.presentation.utils.CircularRevealAnimationUtilClass

interface ViewTaskByIDViewClassInterface {

    interface ViewTaskByIDViewClassListener{

    }

    interface ViewTaskByIDViewClassFragmentListener{
        fun dismissDialogFragment()
        fun launchEditTaskFragment(revealSettings: CircularRevealAnimationUtilClass.RevealAnimationSetting)
    }

    fun getRoot(): View
     fun setFragmentListener(fragmentListener: ViewTaskByIDViewClassFragmentListener)
    fun populateTextViews(task: TaskPresentationModel)
    fun setupToolbar()
    fun getRevealSettings(): CircularRevealAnimationUtilClass.RevealAnimationSetting
}