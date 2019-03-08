package com.fyp.kweku.cbtoganisation.tasks.domain.interactors

import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task

interface CreateNewTaskInteractorInterface {

    fun SendTaskToDataLayer(task: Task): Task
}
