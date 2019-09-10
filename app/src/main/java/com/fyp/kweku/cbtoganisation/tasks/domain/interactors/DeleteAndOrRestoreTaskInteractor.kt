package com.fyp.kweku.cbtoganisation.tasks.domain.interactors

import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces.TaskDeletionOutput
import com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces.TaskOutput
import com.fyp.kweku.cbtoganisation.tasks.domain.repository.TaskRepositoryInterface
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteAndOrRestoreTaskInteractor @Inject constructor(private val taskRepositoryInterface: TaskRepositoryInterface,
                                                           private val taskDeletionOutput: TaskDeletionOutput, private val taskOutput: TaskOutput
):
    DeleteAndOrRestoreTaskInteractorInterface {

    private val scope = CoroutineScope(Job() + Dispatchers.Main)
    private val taskToRestore: Task? = null
    private val communicationChannel: Channel<String> = Channel(-1)

    private suspend fun deleteTask(task: Task){
        taskRepositoryInterface.deleteTask(task)
    }

    private suspend fun undoDelete(){
        taskDeletionOutput.getLastDeletedDeTask()?.let { taskRepositoryInterface.saveTask(it) }
    }




    private suspend fun reloadTasks() =  taskOutput.showAllTasks(taskRepositoryInterface.getAllTasks())

    private fun getTaskToBeDeleted(taskID: String):Deferred<Task> = scope.async(Dispatchers.IO){
       return@async  taskRepositoryInterface.getTaskById(taskID)
    }

    override fun restoreTask(): Job = scope.launch(Dispatchers.IO)  {
        undoDelete()
        reloadTasks()
        taskDeletionOutput.setLastDeletedTaskToNull()
    }



    override fun deleteAndOrRestoreTask(taskID: String): Job = scope.launch(Dispatchers.IO) {
        val task:Deferred<Task> = getTaskToBeDeleted(taskID)
        taskDeletionOutput.setLastDeletedTask(task.await())
        deleteTask(task.await())
        reloadTasks()
    }

}