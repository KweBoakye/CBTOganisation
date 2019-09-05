package com.fyp.kweku.cbtoganisation.tasks.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces.TasksByLocationOutput
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.PresentationModelMapper
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TasksByLocationViewModel @Inject constructor():
    TasksByLocationOutput {

    private val tasksByLocationLiveData: MutableLiveData<List<TaskPresentationModel>> =
        MutableLiveData()
    private val modelMapper = PresentationModelMapper()

    override fun getTasksByLocation(): LiveData<List<TaskPresentationModel>> =
        this.tasksByLocationLiveData

    override suspend fun showTasksByLocation(tasksByLocation: List<Task>) =
        withContext(Dispatchers.Main) {
            tasksByLocationLiveData.value = tasksByLocation.map {
                modelMapper.toEntity(it)
            }
        }


}