package com.fyp.kweku.cbtoganisation.tasks.domain.interactors

import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils
import com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces.TaskOutput
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.domain.repository.TaskRepositoryInterface
import kotlinx.coroutines.*
import kotlinx.coroutines.reactive.*
import org.threeten.bp.LocalDate
import timber.log.Timber
import javax.inject.Inject


class GetTasksInteractor @Inject constructor(
    private val taskRepositoryInterface: TaskRepositoryInterface, private val taskOutput: TaskOutput
) : GetTasksInteractorInterface {


    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    override suspend fun allTasks(): List<Task> = taskRepositoryInterface.getAlltasks()//safe call

    override  fun getTaskByID(taskID: String)= scope.launch(Dispatchers.IO){
       taskOutput.showTask(taskRepositoryInterface.getTaskById(taskID))}

    override suspend fun sendTasksToPresentationLayer() {
        val tasks = allTasks()
         taskOutput.showAllTasks(tasks)
    Timber.i("sendTasksToPresentationLayer used")}

   override suspend fun filterTasksByDay(date: LocalDate) {
       taskOutput.setDateInput(date)
   Timber.i(date.format(ProjectDateTimeUtils.getCustomDateFormatter()))}


}



