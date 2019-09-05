package com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces

import androidx.lifecycle.LiveData
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel

interface TasksByLocationOutput {

    fun getTasksByLocation(): LiveData<List<TaskPresentationModel>>
    suspend fun showTasksByLocation(tasksByLocation: List<Task>)
}