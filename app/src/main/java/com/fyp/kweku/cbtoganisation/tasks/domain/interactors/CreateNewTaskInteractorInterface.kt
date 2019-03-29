package com.fyp.kweku.cbtoganisation.tasks.domain.interactors

import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task

interface CreateNewTaskInteractorInterface {
    fun generateTaskID(): String
    fun createNewTaskObject(task: Task):Task
    suspend fun SendTaskToDataLayer(task: Task): Task
}
