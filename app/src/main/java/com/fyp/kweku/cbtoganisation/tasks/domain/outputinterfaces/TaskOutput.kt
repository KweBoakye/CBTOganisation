package com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import org.threeten.bp.LocalDate

interface TaskOutput {


   fun getAllTasks():LiveData<List<TaskPresentationModel>>
   //fun getTasksByDay(): LiveData<List<TaskPresentationModel>>
    fun getMediatorTasksByDay(): LiveData<MutableList<TaskPresentationModel>>

    fun getMonthCalendarTasksByDay() : LiveData<MutableList<MutableList<TaskPresentationModel>>>

    //fun getMonthCalendarTaskNamesByDay() : MutableLiveData<MutableList<MutableList<String>>>


    fun getSingleTaskLiveData(): LiveData<TaskPresentationModel>

 //fun getDatesAndTasksByMonth(): LiveData<List<Triple<LocalDate, Boolean, MutableList<TaskPresentationModel?>>>>

    suspend fun showAllTasks(tasks: List<Task>)

    suspend fun showTask(task: Task)

    suspend fun setDateInput(date: LocalDate)



}