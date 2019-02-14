package com.fyp.kweku.cbtoganisation.domain.Repository

import com.fyp.kweku.cbtoganisation.domain.Model.Task

interface ITaskRepository {
    fun getAlltasks(): List<Task>
    fun getTaskById(taskId: Int): Task
    fun saveTask()



}