package com.fyp.kweku.cbtoganisation.tasks.presentation.deletetask

interface DeleteTasksViewClassInterface {

    interface DeleteTasksViewClassListener{
        fun restoreTask()
    }

    fun setListener(listener: DeleteTasksViewClassListener)
    fun showSnackbar()
}