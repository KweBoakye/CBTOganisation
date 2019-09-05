package com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces

import androidx.lifecycle.LiveData
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task

interface TaskDeletionOutput {

    suspend fun setLastDeletedTask(task: Task?)
    fun setLastDeletedTaskToNull()
    fun getShouldSnackBarBeLaunched(): LiveData<Boolean>
    fun getLastDeletedDeTask(): Task?
}