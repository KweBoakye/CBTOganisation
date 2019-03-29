package com.fyp.kweku.cbtoganisation.tasks.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.TaskOutput
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.domain.repository.ModelMapper
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel

class TaskViewModel(private val modelMapper: ModelMapper<TaskPresentationModel,Task>) : ViewModel(), TaskOutput {

   val liveTask = MutableLiveData<TaskPresentationModel>()
    val liveTaskList = MutableLiveData<List<TaskPresentationModel>>()

    override fun showAllTasks(tasks: List<Task>) {
        liveTaskList.postValue(tasks.map { modelMapper.toEntity(it) })
    }

    override fun showTask(task: Task){
       liveTask.postValue(modelMapper.toEntity(task))

    }


}
