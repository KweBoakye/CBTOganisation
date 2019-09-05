package com.fyp.kweku.cbtoganisation.tasks.presentation.locations.tasksbylocation

import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.LocationInteractorInterface
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.TasksByLocationInteractorInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class TasksByLocationController(val tasksByLocationInteractorInterface: TasksByLocationInteractorInterface): TasksByLocationViewClassInterface.TasksByLocationViewClassListener {


    private lateinit var tasksByLocationViewClassInterface: TasksByLocationViewClassInterface

    fun loadTasksByLocations(location: String) = tasksByLocationInteractorInterface.sendTasksByLocationToOutput(location)

    fun bindView(tasksByLocationViewClassInterface: TasksByLocationViewClassInterface){
        this.tasksByLocationViewClassInterface = tasksByLocationViewClassInterface
    }


}