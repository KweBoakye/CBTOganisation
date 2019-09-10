package com.fyp.kweku.cbtoganisation.tasks.domain.interactors

import com.fyp.kweku.cbtoganisation.common.UuidSource
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces.TaskOutput
import com.fyp.kweku.cbtoganisation.tasks.domain.repository.TaskRepositoryInterface
import javax.inject.Inject


class CreateNewTaskInteractor @Inject constructor(private val taskRepositoryInterface: TaskRepositoryInterface, private val taskOutput: TaskOutput) : CreateNewTaskInteractorInterface {



     override fun generateTaskID(): String { val ID = UuidSource.random().toString()
        return ID}

     override fun createNewTaskObject(task: Task):Task{val newTaskObject  = task.copy(taskID = generateTaskID())
        return newTaskObject}

override suspend fun sendTaskToDataLayer(task: Task):Task {
    val newTaskObject = createNewTaskObject(task)
    taskRepositoryInterface.saveTask(newTaskObject)
    taskOutput.showAllTasks(taskRepositoryInterface.getAllTasks())
    return newTaskObject}
}