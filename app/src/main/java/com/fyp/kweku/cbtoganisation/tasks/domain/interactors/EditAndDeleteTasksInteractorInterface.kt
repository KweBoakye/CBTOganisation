package com.fyp.kweku.cbtoganisation.tasks.domain.interactors

import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task

interface EditAndDeleteTasksInteractorInterface {



    suspend fun updateTask(task: Task)

    suspend fun deleteTask(task: Task)



}