package com.fyp.kweku.cbtoganisation.tasks.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces.TaskDeletionOutput
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskDeletionViewModel @Inject constructor(): ViewModel(),
    TaskDeletionOutput {


    private val lastDeletedTaskLiveData: MutableLiveData<TaskPresentationModel> = MutableLiveData()
    private var lastDeletedTask: Task? = null
    private val shouldSnackBarBeLaunched: MutableLiveData<Boolean> = MutableLiveData(false)


    override fun getLastDeletedDeTask(): Task? = this.lastDeletedTask

    override fun getShouldSnackBarBeLaunched(): LiveData<Boolean> = this.shouldSnackBarBeLaunched

    private suspend fun setLastDeletedTaskLivedate(task: TaskPresentationModel)= withContext(Dispatchers.Main){
        lastDeletedTaskLiveData.value = task
        shouldSnackBarBeLaunched.value = true
        shouldSnackBarBeLaunched.value = false
    }

    override suspend fun setLastDeletedTask(task: Task?)= withContext(Dispatchers.Main){
      this@TaskDeletionViewModel.lastDeletedTask = task
        shouldSnackBarBeLaunched.value = true
        shouldSnackBarBeLaunched.value = false
    }

    override fun setLastDeletedTaskToNull(){
        this.lastDeletedTask = null
    }



}