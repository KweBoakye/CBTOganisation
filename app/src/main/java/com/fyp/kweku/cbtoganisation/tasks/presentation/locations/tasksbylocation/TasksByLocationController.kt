package com.fyp.kweku.cbtoganisation.tasks.presentation.locations.tasksbylocation


import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.TasksByLocationInteractorInterface

import javax.inject.Inject


class TasksByLocationController @Inject constructor(val tasksByLocationInteractorInterface: TasksByLocationInteractorInterface): TasksByLocationViewClassInterface.TasksByLocationViewClassListener {


    private lateinit var tasksByLocationViewClassInterface: TasksByLocationViewClassInterface

    fun loadTasksByLocations(location: String) = tasksByLocationInteractorInterface.sendTasksByLocationToOutput(location)

    fun bindView(tasksByLocationViewClassInterface: TasksByLocationViewClassInterface){
        this.tasksByLocationViewClassInterface = tasksByLocationViewClassInterface
    }


}