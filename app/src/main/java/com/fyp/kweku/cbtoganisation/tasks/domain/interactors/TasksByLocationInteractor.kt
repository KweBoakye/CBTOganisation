package com.fyp.kweku.cbtoganisation.tasks.domain.interactors

import com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces.TasksByLocationOutput
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.domain.repository.TaskRepositoryInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class TasksByLocationInteractor @Inject constructor(private val taskRepositoryInterface: TaskRepositoryInterface,
                                private val tasksByLocationOutput: TasksByLocationOutput
):TasksByLocationInteractorInterface {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    override suspend fun getTasksByLocation(taskLocation: String): List<Task> {
        return taskRepositoryInterface.getTasksByLocation(taskLocation)
    }

    override fun sendTasksByLocationToOutput(location: String)= scope.launch(Dispatchers.IO) {
        tasksByLocationOutput.showTasksByLocation(getTasksByLocation(location))
    }

}