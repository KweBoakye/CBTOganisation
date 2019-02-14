package com.fyp.kweku.cbtoganisation.domain.interactors

import com.fyp.kweku.cbtoganisation.domain.Repository.ITaskRepository

class GetAllTasksInteractor(
    private val ITaskRepository: ITaskRepository
) {
    fun allTasks() = ITaskRepository.getAlltasks()
}