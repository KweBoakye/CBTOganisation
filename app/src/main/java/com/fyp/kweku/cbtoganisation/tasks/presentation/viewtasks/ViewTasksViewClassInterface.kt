package com.fyp.kweku.cbtoganisation.tasks.presentation.viewtasks

import android.view.View

interface ViewTasksViewClassInterface {

    interface ViewTasksListener{
        fun onTaskClick()
    }

    fun getRootView(): View


    fun setListener(listener: ViewTasksListener?)
}