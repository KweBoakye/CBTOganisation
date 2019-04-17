package com.fyp.kweku.cbtoganisation.tasks.domain.interactors

import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import org.threeten.bp.LocalDate

interface GetTasksInteractorInterface {
    suspend fun allTasks(): List<Task>
    suspend fun getTaskByID(taskID: String): Task
    fun sendSingleTaskToPresentationLayer(task: Task)
    suspend fun sendTasksToPresentationLayer()
    suspend fun filterTasksByDay(date: LocalDate)
    fun getTasksByLiveDataAsAny():Any
    suspend fun filterListOfTasksByDay(listOfDates:MutableList<LocalDate>)
}