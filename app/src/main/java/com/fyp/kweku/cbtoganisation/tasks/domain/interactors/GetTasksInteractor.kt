package com.fyp.kweku.cbtoganisation.tasks.domain.interactors

import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.domain.repository.TaskRepositoryInterface
import kotlinx.coroutines.*
import kotlinx.coroutines.reactive.*



class GetTasksInteractor(
    private val taskRepositoryInterface: TaskRepositoryInterface, private val taskOutput: TaskOutput
) : GetTasksInteractorInterface {



    override suspend fun allTasks(): List<Task> = taskRepositoryInterface.getAlltasks()//safe call

    override suspend fun getTaskByID(taskID: String): Task = taskRepositoryInterface.getTaskById(taskID)

    @ExperimentalCoroutinesApi
    override fun sendSingleTaskToPresentationLayer(task: Task)= runBlocking<Unit>{
val presentTask = publish<Task>{send(task)}
        presentTask.consumeEach { taskOutput.showTask(it) }

    }

    override fun sendTasksToPresentationLayer(tasks: List<Task>) = taskOutput.showAllTasks(tasks)
}


