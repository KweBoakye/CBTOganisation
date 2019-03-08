package com.fyp.kweku.cbtoganisation.tasks.domain.interactors

import com.fyp.kweku.cbtoganisation.common.UuidSource
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.domain.repository.TaskRepositoryInterface




class CreateNewTaskInteractor(private val taskRepositoryInterface: TaskRepositoryInterface) : CreateNewTaskInteractorInterface {

     fun generateTaskID(): String { val ID = UuidSource.random().toString()
        return ID}

     fun createNewTaskObject(task: Task):Task{val newTaskObject  = task.copy(taskID = generateTaskID())
        return newTaskObject}

override fun SendTaskToDataLayer(task: Task):Task {
    val newTaskObject = createNewTaskObject(task)
    taskRepositoryInterface.saveTask(newTaskObject)
    return newTaskObject}
}