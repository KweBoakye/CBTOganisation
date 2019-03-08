package com.fyp.kweku.cbtoganisation.tasks.domain.repository

import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task

interface TaskRepositoryInterface {
    fun getAlltasks(): List<Task>
    fun getTaskById(taskId: Int): Task
    fun saveTask(vararg tasks: Task)
    fun newTask(task:Task)



}