package com.fyp.kweku.cbtoganisation.tasks.domain.interactors

import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.domain.repository.TaskRepositoryInterface

class EditAndDeleteTasksInteractor(private val taskRepositoryInterface: TaskRepositoryInterface): EditAndDeleteTasksInteractorInterface {

override suspend fun updateTask(task:Task){
    taskRepositoryInterface.updateTask(task)
}


   override suspend fun deleteTask(task: Task){
        taskRepositoryInterface.deleteTask(task)
    }
}