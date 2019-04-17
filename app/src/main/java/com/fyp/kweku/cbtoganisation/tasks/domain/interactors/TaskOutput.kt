package com.fyp.kweku.cbtoganisation.tasks.domain.interactors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import org.threeten.bp.LocalDate

interface TaskOutput {



   fun getTasksByDay(): LiveData<MutableList<TaskPresentationModel>>


    suspend fun showAllTasks(tasks: List<Task>)

    fun showTask(task: Task)


   suspend fun postTasksByDay(date: LocalDate)

    suspend fun postMonthCalendarTasksByDay(listOfDates:MutableList<LocalDate>)
}