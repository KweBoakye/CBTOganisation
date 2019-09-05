package com.fyp.kweku.cbtoganisation.tasks.presentation.edittasks

import android.view.View
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel

interface EditTaskViewClassInterface {

    interface EditTaskViewClassListener{
    }

    fun getRoot(): View
    fun setListener(editTaskViewClassListener: EditTaskViewClassListener)
    fun setInitialData(task: TaskPresentationModel)
    fun getTaskUpdateInput(): Array<String>
}