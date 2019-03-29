package com.fyp.kweku.cbtoganisation.tasks.domain.interactors

import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task

interface GetTasksInteractorInterface {
    suspend fun allTasks(): List<Task>
    suspend fun getTaskByID(taskID: String): Task
    fun sendSingleTaskToPresentationLayer(task: Task)
    fun sendTasksToPresentationLayer(tasks: List<Task>)
}