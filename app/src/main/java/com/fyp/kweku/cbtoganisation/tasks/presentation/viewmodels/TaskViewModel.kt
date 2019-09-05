package com.fyp.kweku.cbtoganisation.tasks.presentation.viewmodels

import androidx.lifecycle.*
import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils
import com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces.TaskOutput
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.PresentationModelMapper
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskViewModel @Inject constructor() : ViewModel(),
    TaskOutput {


    private val singleTaskLiveData: MutableLiveData<TaskPresentationModel> = MutableLiveData()
    private val allTasksLiveData: MutableLiveData<List<TaskPresentationModel>> = MutableLiveData()
    private val dateInput = MutableLiveData<LocalDate>()
    private val monthCalendarTasksByDay: MutableLiveData<MutableList<MutableList<TaskPresentationModel>>> = MutableLiveData()
    private val mediatorTasksByDay: MediatorLiveData<MutableList<TaskPresentationModel>> = MediatorLiveData()
    private val modelMapper = PresentationModelMapper()

    init {
        filterTasksByDay()
    }

    override fun getMediatorTasksByDay(): LiveData<MutableList<TaskPresentationModel>> = this.mediatorTasksByDay

    override suspend fun showAllTasks(tasks: List<Task>) = withContext(Dispatchers.Main) {
        allTasksLiveData.value = tasks.map { modelMapper.toEntity(it) }
    }

    override suspend fun showTask(task: Task) = withContext(Dispatchers.Main){
        singleTaskLiveData.value = modelMapper.toEntity(task)
    }

    override fun getSingleTaskLiveData(): LiveData<TaskPresentationModel> = this.singleTaskLiveData

    override fun getAllTasks(): LiveData<List<TaskPresentationModel>> = this.allTasksLiveData

    override fun getMonthCalendarTasksByDay(): LiveData<MutableList<MutableList<TaskPresentationModel>>> = monthCalendarTasksByDay

    override suspend fun setDateInput(date: LocalDate) = withContext(Dispatchers.Main){
        dateInput.value = date
    }


    private fun filterTasksByDay(){
        mediatorTasksByDay.addSource(dateInput ){ postTasksByDay4(allTasksLiveData, dateInput)}
        mediatorTasksByDay.addSource(allTasksLiveData){ postTasksByDay4(allTasksLiveData, dateInput)}

    }

    private fun postTasksByDay4(tasksLiveData: LiveData<List<TaskPresentationModel>>, dateLiveData: LiveData<LocalDate>)  {
       val tasks = tasksLiveData.value
        val date = dateLiveData.value
        if (tasks != null && date != null) {

            val tasksForThisDay: MutableList<TaskPresentationModel> = tasks.filter { taskPresentationModel ->
                ProjectDateTimeUtils.checkIfDateIsInRange(
                    date,
                    taskPresentationModel.taskStartDate,
                    taskPresentationModel.taskEndDate
                )
            }.toMutableList()
            mediatorTasksByDay.value = tasksForThisDay

            Timber.i("${mediatorTasksByDay.value}")
            val a: Boolean = mediatorTasksByDay.hasActiveObservers()

            Timber.i("$a")}
    }




}
