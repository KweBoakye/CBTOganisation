package com.fyp.kweku.cbtoganisation.tasks.domain.interactors


import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import org.threeten.bp.LocalDate

interface GetTasksInteractorInterface {
    suspend fun allTasks(): List<Task>
    suspend fun getTaskByID(taskID: String)
    fun sendSingleTaskToPresentationLayer(task: Task)
    suspend fun sendTasksToPresentationLayer()
    suspend fun filterTasksByDay(date: LocalDate)
    fun getTasksByLiveDataAsAny():Any
    fun getSelectedDateForHomeTitleAsAny():Any
    suspend fun filterListOfTasksByDay(listOfDates:MutableList<LocalDate>)
    fun getMonthCalendarTasksByDayAsAny(): Any
    fun getMonthCalendarTaskNamesByDayAsAny(): Any
    suspend fun filterTasksByMonth(listOfDates: MutableList<Pair<LocalDate, Boolean>>)
    fun getDatesAndTasksByMonthAsAny(): Any
    fun getSingleTaskLiveDataAsAny():Any
    suspend fun getTaskByIDDirectFromPersistence(taskID: String):Task
}