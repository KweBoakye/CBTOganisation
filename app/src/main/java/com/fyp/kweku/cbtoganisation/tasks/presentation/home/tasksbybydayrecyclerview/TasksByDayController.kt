package com.fyp.kweku.cbtoganisation.tasks.presentation.home.tasksbybydayrecyclerview


import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.GetTasksInteractorInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class TasksByDayController(val getTasksInteractorInterface: GetTasksInteractorInterface):TasksByDayRecyclerViewClassInterface.TasksByDayRecyclerViewClassListener {

  private var parentJob = Job()
  private val coroutineContext: CoroutineContext
    get() = parentJob + Dispatchers.Main
  private val scope = CoroutineScope(coroutineContext)


  fun loadAllTasksForRecycler() = scope.launch(Dispatchers.IO){ getTasksInteractorInterface.sendTasksToPresentationLayer()}





    fun onCreate(){}
}