package com.fyp.kweku.cbtoganisation.tasks.domain.interactors

import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils
import com.fyp.kweku.cbtoganisation.tasks.domain.TaskOutput
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.domain.repository.TaskRepositoryInterface
import kotlinx.coroutines.*
import kotlinx.coroutines.reactive.*
import org.threeten.bp.LocalDate
import timber.log.Timber


class GetTasksInteractor(
    private val taskRepositoryInterface: TaskRepositoryInterface, private val taskOutput: TaskOutput
) : GetTasksInteractorInterface {

    override fun getTasksByLiveDataAsAny(): Any {
        return taskOutput.getTasksByDay()
    }

    override fun getSelectedDateForHomeTitleAsAny():Any = taskOutput.getSelectedDateForHomeTitle()

    override fun getMonthCalendarTasksByDayAsAny(): Any {
        return  taskOutput.getMonthCalendarTasksByDay()
    }

    override fun getMonthCalendarTaskNamesByDayAsAny(): Any {
        return  taskOutput.getMonthCalendarTaskNamesByDay()
    }

    override fun getDatesAndTasksByMonthAsAny(): Any{
        return taskOutput.getDatesAndTasksByMonth()
    }

    override fun getSingleTaskLiveDataAsAny():Any{
        return taskOutput.getSingleTaskLiveData()
    }



   override suspend fun getTaskByIDDirectFromPersistence(taskID: String):Task = taskRepositoryInterface.getTaskById(taskID)

    override suspend fun allTasks(): List<Task> = taskRepositoryInterface.getAlltasks()//safe call

    override suspend fun getTaskByID(taskID: String){
        sendSingleTaskToPresentationLayer(taskRepositoryInterface.getTaskById(taskID))}

    @ExperimentalCoroutinesApi
    override fun sendSingleTaskToPresentationLayer(task: Task)= runBlocking<Unit>{

val presentTask = publish<Task>{send(task)}
        presentTask.consumeEach { taskOutput.showTask(it) }

    }

    override suspend fun sendTasksToPresentationLayer() {
        val tasks = allTasks()
         taskOutput.showAllTasks(tasks)
    Timber.i("sendTasksToPresentationLayer used")}



   override suspend fun filterTasksByDay(date: LocalDate) {
       taskOutput.postTasksByDay(date)
   Timber.i("${date.format(ProjectDateTimeUtils.getCustomDateFormatter())}")}

    override suspend fun filterListOfTasksByDay(listOfDates: MutableList<LocalDate>) {
        taskOutput.postMonthCalendarTasksByDay(listOfDates)
    }

    override suspend fun filterTasksByMonth(listOfDates: MutableList<Pair<LocalDate, Boolean>>){
        taskOutput.setDatesAndTasksByMonth(listOfDates)
    }

}



