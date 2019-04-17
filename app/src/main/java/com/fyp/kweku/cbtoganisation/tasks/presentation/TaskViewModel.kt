package com.fyp.kweku.cbtoganisation.tasks.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils

import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.TaskOutput
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.PresentationModelMapper
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate
import timber.log.Timber

class TaskViewModel : ViewModel(), TaskOutput {



    val singleTaskLiveData: MutableLiveData<TaskPresentationModel> = MutableLiveData()
    val allTasksLiveData: MutableLiveData<List<TaskPresentationModel>> = MutableLiveData()
    private val dateInput = MutableLiveData<LocalDate>()
   val tasksByDay: MutableLiveData<MutableList<TaskPresentationModel>> = MutableLiveData()
    //override val tasksByDayOutput: MutableLiveData<List<TaskPresentationModel>> =
val monthCalendarTasksByDay : MutableLiveData<MutableList<MutableList<TaskPresentationModel>>> = MutableLiveData()
    val modelMapper = PresentationModelMapper()



    override suspend fun showAllTasks(tasks: List<Task>) = withContext(Dispatchers.Main) {
        allTasksLiveData.value = tasks.map { modelMapper.toEntity(it) }

    }


    override fun showTask(task: Task){
        singleTaskLiveData.setValue(modelMapper.toEntity(task))



    }

    fun getAllTasks(): LiveData<List<TaskPresentationModel>> {
        return this.allTasksLiveData
    }
    override fun getTasksByDay(): LiveData<MutableList<TaskPresentationModel>>{return tasksByDay}

    override suspend fun postTasksByDay(date: LocalDate)= withContext(Dispatchers.Main){
        val tasksForThisDay: List<TaskPresentationModel> =allTasksLiveData.value!!.filter { taskPresentationModel ->
            ProjectDateTimeUtils.checkIfDateIsInRange(date, taskPresentationModel.taskStartDate, taskPresentationModel.taskEndDate)
        }
        tasksByDay.setValue( tasksForThisDay.toMutableList())
        Timber.i("${tasksByDay.value}")
        val a:Boolean = tasksByDay.hasActiveObservers()

        Timber.i("$a")
    }

    override suspend fun postMonthCalendarTasksByDay(listOfDates:MutableList<LocalDate>)= withContext(Dispatchers.Main){
        val taskList: MutableList<MutableList<TaskPresentationModel>> = mutableListOf()
        listOfDates.forEachIndexed { index, date ->
            taskList.add(index,
                allTasksLiveData.value!!.filter { taskPresentationModel ->
                    ProjectDateTimeUtils.checkIfDateIsInRange(date, taskPresentationModel.taskStartDate, taskPresentationModel.taskEndDate)
                }.toMutableList())}
        monthCalendarTasksByDay.value = taskList
        }


}
