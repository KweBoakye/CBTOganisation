package com.fyp.kweku.cbtoganisation.tasks.presentation.home.tasksbybydayrecyclerview


import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.GetTasksInteractorInterface
import com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces.TaskOutput
import com.fyp.kweku.cbtoganisation.tasks.presentation.deletetask.DeleteTasksController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class TasksByDayController @Inject constructor(val getTasksInteractorInterface: GetTasksInteractorInterface, val taskOutput : TaskOutput):
  TasksByDayRecyclerViewClassInterface.TasksByDayRecyclerViewClassListener {

  private var parentJob = Job()
  private val coroutineContext: CoroutineContext
    get() = parentJob + Dispatchers.Main
  private val scope = CoroutineScope(coroutineContext)
  private lateinit var deleteTasksController: DeleteTasksController
  private lateinit var tasksByDayRecyclerViewClassInterface: TasksByDayRecyclerViewClassInterface


  fun loadAllTasksForRecycler() = scope.launch(Dispatchers.IO){ getTasksInteractorInterface.sendTasksToPresentationLayer()}

  fun setTasksByDayRecyclerViewClassInterface(tasksByDayRecyclerViewClassInterface: TasksByDayRecyclerViewClassInterface){
    this.tasksByDayRecyclerViewClassInterface = tasksByDayRecyclerViewClassInterface
    tasksByDayRecyclerViewClassInterface.setTasksByDayRecyclerViewClassListener(this)
  }

  fun setDeleteTasksController(deleteTasksController: DeleteTasksController){
    this.deleteTasksController = deleteTasksController
  }

  override fun deleteTask(taskID: String){
    deleteTasksController.deleteTask(taskID)
  }


    fun onCreate(){}
}