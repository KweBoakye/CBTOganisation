package com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces

import androidx.lifecycle.LiveData

import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import org.threeten.bp.LocalDate

interface TaskOutput {


   fun getAllTasks():LiveData<List<TaskPresentationModel>>
    fun getMediatorTasksByDay(): LiveData<MutableList<TaskPresentationModel>>

    fun getMonthCalendarTasksByDay() : LiveData<MutableList<MutableList<TaskPresentationModel>>>

    fun getSingleTaskLiveData(): LiveData<TaskPresentationModel>

    suspend fun showAllTasks(tasks: List<Task>)

    suspend fun showTask(task: Task)

    suspend fun setDateInput(date: LocalDate)



}