package com.fyp.kweku.cbtoganisation.tasks.domain.repository

import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task

interface TaskRepositoryInterface {
    suspend fun getAlltasks(): List<Task>
    suspend fun getTaskById(taskId: String): Task
    suspend fun saveTask(vararg tasks: Task)
    suspend fun newTask(task:Task)



}