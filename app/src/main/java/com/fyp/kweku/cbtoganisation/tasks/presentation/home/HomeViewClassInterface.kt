package com.fyp.kweku.cbtoganisation.tasks.presentation.home


import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.fyp.kweku.cbtoganisation.tasks.presentation.TaskActivity


interface HomeViewClassInterface {

    interface HomeListener{

    }

    interface HomeFragmentListener{
        fun onGoToCreateNewTaskFragmentButtonClicked(taskActivity: TaskActivity)
    }

    fun getRootView(): View

    fun setToolbar()

    //fun setToolbarDate(date: LocalDate)

    fun setListener(listener: HomeListener)

    fun getTaskActivity(): TaskActivity

    fun setTaskActivity(taskActivity: TaskActivity)

    fun setGoToCreateNewTaskFragmentButtonOnClickListener()

    fun gethomeCoordinatorLayout():CoordinatorLayout



}