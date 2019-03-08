package com.fyp.kweku.cbtoganisation.tasks.domain.interactors

import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.domain.repository.TaskRepositoryInterface


class GetAllTasksInteractor(
    private val TaskRepositoryInterface: TaskRepositoryInterface
)
{
    fun allTasks(): List<Task>? = TaskRepositoryInterface.getAlltasks()//safe call
}
