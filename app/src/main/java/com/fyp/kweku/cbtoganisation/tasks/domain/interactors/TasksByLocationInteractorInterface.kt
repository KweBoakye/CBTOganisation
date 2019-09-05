package com.fyp.kweku.cbtoganisation.tasks.domain.interactors

import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import kotlinx.coroutines.Job

interface TasksByLocationInteractorInterface {

    suspend fun getTasksByLocation(taskLocation: String): List<Task>
    fun sendTasksByLocationToOutput(location: String):Job
}