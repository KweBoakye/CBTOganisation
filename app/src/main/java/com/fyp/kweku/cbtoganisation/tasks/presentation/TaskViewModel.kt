package com.fyp.kweku.cbtoganisation.tasks.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils
import com.fyp.kweku.cbtoganisation.tasks.domain.TaskOutput
import com.fyp.kweku.cbtoganisation.tasks.domain.TaskPresenter
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.PresentationModelMapper
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate
import timber.log.Timber

class TaskViewModel(private val taskPresenter: TaskPresenter) : ViewModel(),
    TaskOutput {


    private val singleTaskLiveData: MutableLiveData<TaskPresentationModel> = MutableLiveData()
    val allTasksLiveData: MutableLiveData<List<TaskPresentationModel>> = MutableLiveData()
    private val dateInput = MutableLiveData<LocalDate>()
    val tasksByDay: MutableLiveData<MutableList<TaskPresentationModel>> = MutableLiveData()
    private val selectedDateForHomeTitle: MutableLiveData<LocalDate> = MutableLiveData()
    //override val tasksByDayOutput: MutableLiveData<List<TaskPresentationModel>> =
    val monthCalendarTasksByDay: MutableLiveData<MutableList<MutableList<TaskPresentationModel>>> = MutableLiveData()
    private val monthCalendarTaskNamesByDay: MutableLiveData<MutableList<MutableList<String>>> = MutableLiveData()
    private val allLocations: MutableLiveData<List<String>> = MutableLiveData()

    private val tasksByLocationLiveData: MutableLiveData<List<TaskPresentationModel>> = MutableLiveData()
    private val datesAndTasksByMonth: MutableLiveData<List<Triple<LocalDate, Boolean, MutableList<TaskPresentationModel?>>>> = MutableLiveData()
    private val locationsSeatchString: MutableLiveData<String> = MutableLiveData()
    private var filteredLocations: LiveData<List<String>> = Transformations.switchMap(locationsSeatchString) {string->  filterLocations(string)}
    val modelMapper = PresentationModelMapper()

    override fun getAllLocations(): LiveData<List<String>> {
        Timber.i("${allLocations.value}")
        Timber.i("${allLocations.hasActiveObservers()}")
        return this.allLocations
    }





    override suspend fun showAllTasks(tasks: List<Task>) = withContext(Dispatchers.Main) {
        allTasksLiveData.value = tasks.map { modelMapper.toEntity(it) }
    }





    override suspend fun showTask(task: Task) = withContext(Dispatchers.Main){
        singleTaskLiveData.value = modelMapper.toEntity(task)
    }

    override fun getSingleTaskLiveData(): LiveData<TaskPresentationModel> = this.singleTaskLiveData

    fun getAllTasks(): LiveData<List<TaskPresentationModel>> = this.allTasksLiveData

    override fun getTasksByLocation(): MutableLiveData<List<TaskPresentationModel>> = this.tasksByLocationLiveData

    override suspend fun showAllLocations(locations: List<String>) = withContext(Dispatchers.Main) {
        allLocations.value = locations
    }

    override suspend fun setLocationsSearchString(searchString: String) = withContext(Dispatchers.Main){
        locationsSeatchString.value = searchString
    }

    override fun getFilteredLocations():LiveData<List<String>> = filteredLocations

    fun filterLocations(searchString:String):LiveData<List<String>>{
        val results: MutableLiveData<List<String>> = MutableLiveData()
        allLocations.value?.let {
            val locations: List<String> = it
            results.value = locations.filter { location -> location.contains(searchString, true) }
        }
        return results
    }

    override suspend fun showTasksByLocation(tasksByLocation: List<Task>) = withContext(Dispatchers.Main) {
        tasksByLocationLiveData.value = tasksByLocation.map {
            modelMapper.toEntity(it)
        }

    }


    override fun getTasksByDay(): LiveData<MutableList<TaskPresentationModel>> = tasksByDay

    override fun getSelectedDateForHomeTitle(): LiveData<LocalDate>  = this.selectedDateForHomeTitle

    override fun getMonthCalendarTasksByDay(): LiveData<MutableList<MutableList<TaskPresentationModel>>> = monthCalendarTasksByDay

    override fun getMonthCalendarTaskNamesByDay(): MutableLiveData<MutableList<MutableList<String>>> =
        this.monthCalendarTaskNamesByDay


    override fun getDatesAndTasksByMonth(): LiveData<List<Triple<LocalDate, Boolean, MutableList<TaskPresentationModel?>>>> = this.datesAndTasksByMonth

    override suspend fun postTasksByDay(date: LocalDate) = withContext(Dispatchers.Main) {
        val tasksForThisDay: List<TaskPresentationModel> = allTasksLiveData.value!!.filter { taskPresentationModel ->
            ProjectDateTimeUtils.checkIfDateIsInRange(
                date,
                taskPresentationModel.taskStartDate,
                taskPresentationModel.taskEndDate
            )
        }
        tasksByDay.setValue(tasksForThisDay.toMutableList())
        selectedDateForHomeTitle.value = date
        Timber.i("${tasksByDay.value}")
        val a: Boolean = tasksByDay.hasActiveObservers()

        Timber.i("$a")
    }

    override suspend fun postMonthCalendarTasksByDay(listOfDates: MutableList<LocalDate>) =
        withContext(Dispatchers.Main) {
            val taskList: MutableList<MutableList<TaskPresentationModel>> = mutableListOf()

            listOfDates.forEachIndexed { index, date ->
                taskList.add(index,
                    allTasksLiveData.value!!.filter { taskPresentationModel ->
                        ProjectDateTimeUtils.checkIfDateIsInRange(
                            date,
                            taskPresentationModel.taskStartDate,
                            taskPresentationModel.taskEndDate
                        )
                    }.toMutableList()
                )
            }


            val taskNameList: MutableList<MutableList<String>> = taskList.map { list ->
                list.map { taskPresentationModel -> taskPresentationModel.taskName }.toMutableList()
            }.toMutableList()

            monthCalendarTasksByDay.value = taskList
            val checkedTasksNamesList: MutableList<MutableList<String>> =
                taskPresenter.taskNamesListsChecker(taskNameList)
            monthCalendarTaskNamesByDay.value = checkedTasksNamesList
            Timber.i("${monthCalendarTaskNamesByDay.value}")

            monthCalendarTasksByDay.value = taskList
            Timber.i("${monthCalendarTasksByDay.value}")
            val a: Boolean = monthCalendarTasksByDay.hasActiveObservers()
            Timber.i("$a")
        }

    private suspend fun setDatesAndTasksByMonthLiveData(list: List<Triple<LocalDate, Boolean, MutableList<TaskPresentationModel?>>>)= withContext(Dispatchers.Main){
        datesAndTasksByMonth.value = list
        Timber.i("${datesAndTasksByMonth.value}")
        Timber.i("${datesAndTasksByMonth.hasActiveObservers()}")
    }

    override suspend fun setDatesAndTasksByMonth(listOfDates:MutableList<Pair<LocalDate, Boolean>>) /*= withContext(Dispatchers.Main) */{
        val taskList: MutableList<MutableList<TaskPresentationModel?>> = mutableListOf()

        listOfDates.forEachIndexed { index, date ->
            taskList.add(
                index,
                allTasksLiveData.value!!.filter { taskPresentationModel ->
                    ProjectDateTimeUtils.checkIfDateIsInRange(
                        date.first,
                        taskPresentationModel.taskStartDate,
                        taskPresentationModel.taskEndDate
                    )
                }.toMutableList()
            )
        }

        val datesAndTaskZippedList: List<Triple<LocalDate, Boolean, MutableList<TaskPresentationModel?>>> = MutableList(42){ Triple(listOfDates[it].first, listOfDates[it].second, taskList[it])   }    //zip(taskList)
        setDatesAndTasksByMonthLiveData(datesAndTaskZippedList)
       /* datesAndTasksByMonth.value = datesAndTaskZippedList
        Timber.i("${datesAndTasksByMonth.value}")
        Timber.i("${datesAndTasksByMonth.hasActiveObservers()}")*/
        //val tasksAndDatesMap: Map<List<LocalDate>, MutableList<MutableList<TaskPresentationModel>>> = mapOf(listOf, taskList)
    }


}
