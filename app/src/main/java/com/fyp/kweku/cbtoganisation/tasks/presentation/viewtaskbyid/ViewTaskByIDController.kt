package com.fyp.kweku.cbtoganisation.tasks.presentation.viewtaskbyid

import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.GetTasksInteractorInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ViewTaskByIDController(val getTasksInteractorInterface: GetTasksInteractorInterface) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    fun loadTask(taskid: String) = scope.launch(Dispatchers.IO) { getTasksInteractorInterface.getTaskByID(taskid) }

    fun observeTask():Any {
       return getTasksInteractorInterface.getSingleTaskLiveDataAsAny()
    }
}