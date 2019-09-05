package com.fyp.kweku.cbtoganisation.tasks.domain.interactors


import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import kotlinx.coroutines.Job
import org.threeten.bp.LocalDate

interface GetTasksInteractorInterface {
    suspend fun allTasks(): List<Task>
    fun getTaskByID(taskID: String):Job
    suspend fun sendTasksToPresentationLayer()
    suspend fun filterTasksByDay(date: LocalDate)
}