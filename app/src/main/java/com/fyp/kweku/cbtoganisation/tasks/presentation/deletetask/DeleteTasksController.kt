package com.fyp.kweku.cbtoganisation.tasks.presentation.deletetask

import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.DeleteAndOrRestoreTaskInteractorInterface
import javax.inject.Inject


class DeleteTasksController @Inject constructor(private val deleteAndOrRestoreTaskInteractorInterface : DeleteAndOrRestoreTaskInteractorInterface) :
    DeleteTasksViewClassInterface.DeleteTasksViewClassListener{



    private lateinit var deleteTasksViewClassInterface: DeleteTasksViewClassInterface

    fun setDeleteTasksViewClassInterface(deleteTasksViewClassInterface: DeleteTasksViewClassInterface ){
        this.deleteTasksViewClassInterface = deleteTasksViewClassInterface
        deleteTasksViewClassInterface.setListener(this)
    }

    fun deleteTask(taskId: String) {
        deleteAndOrRestoreTaskInteractorInterface.deleteAndOrRestoreTask(taskId)
    deleteTasksViewClassInterface.showSnackbar()
    }

    fun askToRestoreTask(showMessage: Boolean){
        if (showMessage){deleteTasksViewClassInterface.showSnackbar()}
    }

    override fun restoreTask(){
        deleteAndOrRestoreTaskInteractorInterface.restoreTask()
    }


}