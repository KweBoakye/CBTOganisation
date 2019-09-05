package com.fyp.kweku.cbtoganisation.tasks.presentation.edittasks

import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.EditTasksInteractorInterface
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.GetTasksInteractorInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel

class EditTaskController(private  val editTasksInteractorInterface: EditTasksInteractorInterface, private val getTasksInteractorInterface: GetTasksInteractorInterface): EditTaskViewClassInterface.EditTaskViewClassListener {


    private lateinit var editTaskViewClassInterface: EditTaskViewClassInterface
    private lateinit var task: TaskPresentationModel

    fun bindView(editTaskViewClassInterface: EditTaskViewClassInterface){
        this.editTaskViewClassInterface = editTaskViewClassInterface
        editTaskViewClassInterface.setListener(this)
    }

    fun getTask(taskID: String) {
        getTasksInteractorInterface.getTaskByID(taskID)
    }

    fun updateTask(){
        editTasksInteractorInterface.receiveTaskInput(editTaskViewClassInterface.getTaskUpdateInput())
    }






}