package com.fyp.kweku.cbtoganisation.tasks.presentation.viewtaskbyid

import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.GetTasksInteractorInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.utils.CircularRevealAnimationUtilClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ViewTaskByIDController(val getTasksInteractorInterface: GetTasksInteractorInterface) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)
    private lateinit var viewTaskByIDViewClassInterface: ViewTaskByIDViewClassInterface

    fun loadTask(taskid: String) = scope.launch(Dispatchers.IO) { getTasksInteractorInterface.getTaskByID(taskid) }



    fun setViewTaskByIDViewClassInterface(viewTaskByIDViewClassInterface: ViewTaskByIDViewClassInterface){
        this.viewTaskByIDViewClassInterface = viewTaskByIDViewClassInterface
    }

    fun constructRevealSettings(): CircularRevealAnimationUtilClass.RevealAnimationSetting{
        return viewTaskByIDViewClassInterface.getRevealSettings()
    }
}