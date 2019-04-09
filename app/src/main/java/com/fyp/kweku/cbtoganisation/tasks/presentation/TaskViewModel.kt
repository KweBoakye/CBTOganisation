package com.fyp.kweku.cbtoganisation.tasks.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils

import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.TaskOutput
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.PresentationModelMapper
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import timber.log.Timber

class TaskViewModel : ViewModel(), TaskOutput {



    val singleTaskLiveData: MutableLiveData<TaskPresentationModel> = MutableLiveData()
    val allTasksLiveData: MutableLiveData<List<TaskPresentationModel>> = MutableLiveData()
    private val dateInput = MutableLiveData<LocalDate>()
   val tasksByDay: MutableLiveData<MutableList<TaskPresentationModel>> = MutableLiveData()
    //override val tasksByDayOutput: MutableLiveData<List<TaskPresentationModel>> =

    val modelMapper = PresentationModelMapper()

fun test():MutableLiveData<MutableList<TaskPresentationModel>>{val a:MutableLiveData<MutableList<TaskPresentationModel>> =  MutableLiveData()
    a.value = mutableListOf(
    TaskPresentationModel("a",
        "bbbb",
        "here",
        "tasl",
        LocalDate.parse("20/02/2019",ProjectDateTimeUtils.getCustomDateFormatter()),
        LocalDate.parse("20/02/2019",ProjectDateTimeUtils.getCustomDateFormatter()),
        LocalTime.parse("12:20"),
        LocalTime.parse("13:40")))
return a}

    override suspend fun showAllTasks(tasks: List<Task>) = withContext(Dispatchers.Main) {
        allTasksLiveData.value = tasks.map { modelMapper.toEntity(it) }
        //Timber.i("live data set ${tasks[1].taskName}")
        val a =allTasksLiveData.value
        //Timber.i("$a!![1].taskName")
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

}
