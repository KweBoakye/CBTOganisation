package com.fyp.kweku.cbtoganisation.tasks.domain.interactors

import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task

interface TaskOutput {
    fun showAllTasks(tasks: List<Task>)

    fun showTask(task: Task)
}