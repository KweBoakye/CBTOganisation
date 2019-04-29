package com.fyp.kweku.cbtoganisation.tasks.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import org.threeten.bp.LocalDate

interface TaskOutput {



   fun getTasksByDay(): LiveData<MutableList<TaskPresentationModel>>

    fun getMonthCalendarTasksByDay() : LiveData<MutableList<MutableList<TaskPresentationModel>>>

    fun getMonthCalendarTaskNamesByDay() : MutableLiveData<MutableList<MutableList<String>>>

    fun getAllLocations():LiveData<List<String>>

    fun getTasksByLocation(): MutableLiveData<List<TaskPresentationModel>>

 fun getDatesAndTasksByMonth(): LiveData<List<Triple<LocalDate, Boolean, MutableList<TaskPresentationModel?>>>>

    suspend fun showAllTasks(tasks: List<Task>)

    fun showTask(task: Task)


   suspend fun postTasksByDay(date: LocalDate)

    suspend fun postMonthCalendarTasksByDay(listOfDates:MutableList<LocalDate>)

    suspend fun showAllLocations(locations: List<String>)

    suspend fun  showTasksByLocation(tasksByLocation: List<Task>)

    suspend fun setDatesAndTasksByMonth(listOfDates: MutableList<Pair<LocalDate, Boolean>>)

}