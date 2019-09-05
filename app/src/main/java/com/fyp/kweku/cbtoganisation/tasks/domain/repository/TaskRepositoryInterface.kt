package com.fyp.kweku.cbtoganisation.tasks.domain.repository

import com.fyp.kweku.cbtoganisation.tasks.data.model.TaskDataModel
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import org.threeten.bp.LocalDate

interface TaskRepositoryInterface {
    suspend fun getAlltasks(): List<Task>
    suspend fun getTaskById(taskId: String): Task
    suspend fun saveTask(vararg tasks: Task)
    suspend fun getAllLocations():List<String>
    suspend fun getTasksByLocation(taskLocation:String):List<Task>
    suspend fun deleteTask(task: Task):Int
    suspend fun updateTask(task: Task)
    suspend fun getTaskBy42CalendarMonth(startDate: LocalDate, endDate: LocalDate): List<Task>



}