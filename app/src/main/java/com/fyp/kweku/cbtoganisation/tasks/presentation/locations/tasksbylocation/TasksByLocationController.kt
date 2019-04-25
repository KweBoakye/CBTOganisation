package com.fyp.kweku.cbtoganisation.tasks.presentation.locations.tasksbylocation

import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.GetTasksByLocationInteractorInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class TasksByLocationController(val getTasksByLocationInteractorInterface: GetTasksByLocationInteractorInterface): TasksByLocationViewClassInterface.TasksByLocationViewClassListener {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)
    private lateinit var tasksByLocationViewClassInterface: TasksByLocationViewClassInterface

    fun loadTasksByLocations(location: String) = scope.launch(Dispatchers.IO){getTasksByLocationInteractorInterface.sendTasksByLocationToOutput(location)}



    fun bindView(tasksByLocationViewClassInterface: TasksByLocationViewClassInterface){
        this.tasksByLocationViewClassInterface = tasksByLocationViewClassInterface
    }


}