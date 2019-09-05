package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.taskbydaydialog

import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.GetTasksInteractorInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class TasksBySpecificDayController @Inject constructor(val getTasksInteractorInterface: GetTasksInteractorInterface) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)
    private lateinit var tasksBySpecificDayViewClassInterface: TasksBySpecificDayViewClassInterface

    fun loadTasksByDay(date: LocalDate) = scope.launch(Dispatchers.IO) {  getTasksInteractorInterface.filterTasksByDay(date)}
}