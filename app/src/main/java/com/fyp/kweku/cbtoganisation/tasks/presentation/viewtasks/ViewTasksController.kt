package com.fyp.kweku.cbtoganisation.tasks.presentation.viewtasks

import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.GetTasksInteractorInterface

class ViewTasksController(val getTasksInteractorInterface: GetTasksInteractorInterface) {

    private lateinit var viewTasksViewClassInterface: ViewTasksViewClassInterface

    fun bindView(viewTasksViewClassInterface: ViewTasksViewClassInterface){
        this.viewTasksViewClassInterface = viewTasksViewClassInterface
    }
}